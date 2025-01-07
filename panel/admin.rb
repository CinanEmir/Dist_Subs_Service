require 'socket'
require 'google/protobuf'
require 'logger'
require_relative "C:/Users/emirc/Desktop/Mobil Proje/Dist_Subs_Service/panel/capacity_pb"

PYTHON_SERVER_PORT = 6000 
ADMIN_HOST = 'localhost'
RECONNECT_TIMEOUT = 5 # Yeniden bağlanma süresi
MAX_RETRIES = 3 # Maksimum yeniden deneme sayısı

class AdminPanel
  SERVER_PORTS = [4001, 4002, 4003]

  def initialize
    @server_sockets = {}
    @plotter_socket = nil
    @logger = setup_logger
    @connection_retries = Hash.new(0)
  end

  def setup_logger
    logger = Logger.new('admin_panel.log')
    logger.level = Logger::INFO
    logger.formatter = proc do |severity, datetime, progname, msg|
      "#{datetime}: [#{severity}] #{msg}\n"
    end
    logger
  end

  def start_server
    SERVER_PORTS.each do |port|
      Thread.new do
        setup_server_connection(port)
      end
    end
  end

  private

  def setup_server_connection(port)
    begin
      server = TCPServer.new(port)
      @logger.info("Server listening on port #{port}")

      loop do
        begin
          client = server.accept
          handle_client(client, port)
        rescue => e
          @logger.error("Error handling client on port #{port}: #{e.message}")
          handle_connection_error(port)
        ensure
          client&.close
        end
      end
    rescue => e
      @logger.error("Failed to start server on port #{port}: #{e.message}")
      retry_connection(port)
    end
  end

  def handle_client(socket, port)
    @logger.info("Client connected on port #{port}")
    @connection_retries[port] = 0 # Başarılı bağlantıda sayacı sıfırla

    begin
      while (data = socket.readpartial(1024))
        process_capacity_data(data, port)
      end
    rescue EOFError
      @logger.info("Client disconnected on port #{port}")
    rescue => e
      @logger.error("Error processing client on port #{port}: #{e.message}")
      handle_connection_error(port)
    end
  end

  def process_capacity_data(data, port)
    capacity = Capacity.decode(data)
    @logger.info("Received capacity on port #{port}: #{capacity.subscriber_count}")
    
    if valid_subscriber_count?(capacity.subscriber_count)
      send_to_plotter(capacity.subscriber_count, port)
    else
      @logger.warn("Invalid subscriber count received on port #{port}")
    end
  end

  def valid_subscriber_count?(count)
    !count.nil? && count.to_s.match?(/^\d+$/)
  end

  def send_to_plotter(subscriber_count, port)
    begin
      establish_plotter_connection unless @plotter_socket&.alive?
      
      capacity = Capacity.new(
        server_port: port,
        subscriber_count: subscriber_count.to_i
      )
      
      @plotter_socket.write(capacity.to_proto)
      @plotter_socket.flush
      @logger.info("Sent subscriber count to plotter: #{subscriber_count}")
    rescue => e
      @logger.error("Error sending to plotter: #{e.message}")
      reset_plotter_connection
    end
  end

  def establish_plotter_connection
    @plotter_socket = TCPSocket.new(ADMIN_HOST, PYTHON_SERVER_PORT)
    @logger.info("Established new plotter connection")
  end

  def reset_plotter_connection
    @plotter_socket&.close
    @plotter_socket = nil
  end

  def handle_connection_error(port)
    @connection_retries[port] += 1
    if @connection_retries[port] >= MAX_RETRIES
      @logger.error("Max retries reached for port #{port}")
    end
  end

  def retry_connection(port)
    if @connection_retries[port] < MAX_RETRIES
      @logger.info("Retrying connection on port #{port}")
      sleep RECONNECT_TIMEOUT
      setup_server_connection(port)
    end
  end
end

admin_panel = AdminPanel.new

loop do
  admin_panel.start_server
  sleep(5)
end