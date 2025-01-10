import socket
from Capacity_pb2 import Capacity
import matplotlib.pyplot as plt
import time
import logging
from collections import defaultdict
import threading

class SubscriberMonitor:
    HOST = 'localhost'
    PORT = 6000
    DISCONNECT_THRESHOLD = 5  # Saniye
    MAX_SUBSCRIBERS = 5  # Y ekseni limiti
    UPDATE_INTERVAL = 0.01  # Grafik güncelleme aralığı
    SOCKET_TIMEOUT = 1

    def __init__(self):
        self.subscriber_counts = defaultdict(int)
        self.last_update_times = defaultdict(lambda: time.time())
        self.setup_logging()
        self.lock = threading.Lock()
        self.running = True

    def setup_logging(self):
        logging.basicConfig(
            level=logging.INFO,
            format='%(asctime)s - %(levelname)s - %(message)s',
            handlers=[
                logging.FileHandler('abone_monitor.log'),
                logging.StreamHandler()
            ]
        )
        self.logger = logging.getLogger(__name__)

    def create_plot(self):
        plt.ion()
        self.fig, self.ax = plt.subplots(figsize=(10, 6))
        self.fig.canvas.manager.set_window_title('Gerçek Zamanlı Abone Monitörü')
        # Basit stil ayarları
        self.ax.set_facecolor('#f0f0f0')
        self.fig.patch.set_facecolor('white')

    def update_plot(self):
        self.ax.clear()
        servers = list(self.subscriber_counts.keys())
        values = list(self.subscriber_counts.values())

        bars = self.ax.bar(servers, values, edgecolor='black')
        
        # Bar renklendirme
        colors = ['#2ecc71', '#3498db', '#e74c3c']  
        for bar, color in zip(bars, colors):
            bar.set_color(color)
            
        # Değerleri barların üzerine ekle
        for bar in bars:
            height = bar.get_height()
            self.ax.text(bar.get_x() + bar.get_width()/2., height,
                        f'{int(height)}',
                        ha='center', va='bottom')

        self.ax.set_ylabel('Abone Sayısı (Normalize Edilmiş)', fontsize=12)
        self.ax.set_title('Gerçek Zamanlı Abone Dağılımı', fontsize=14)
        self.ax.set_ylim(0, self.MAX_SUBSCRIBERS)
        
        # Grid ekle
        self.ax.grid(True, linestyle='--', alpha=0.7)
        
        plt.tight_layout()
        plt.draw()
        plt.pause(self.UPDATE_INTERVAL)

    def check_stale_connections(self):
        current_time = time.time()
        with self.lock:
            for server, last_update in self.last_update_times.items():
                if current_time - last_update > self.DISCONNECT_THRESHOLD:
                    if self.subscriber_counts[server] != 0:
                        self.logger.warning(f"{server} bağlantısı eskidi - sayıyı sıfırlıyor")
                        self.subscriber_counts[server] = 0

    def process_capacity_data(self, data):
        capacity = Capacity()
        try:
            capacity.ParseFromString(data)
            server_name = f"Sunucu{capacity.server_port % 4000}"
            
            with self.lock:
                self.subscriber_counts[server_name] = capacity.subscriber_count
                self.last_update_times[server_name] = time.time()
                
            self.logger.info(f"{server_name} güncellendi: {capacity.subscriber_count} abone")
            return True
        except Exception as e:
            self.logger.error(f"Kapsite verisi ayrıştırılamadı: {e}")
            return False

    def handle_client_connection(self, conn, addr):
        self.logger.info(f"{addr} adresinden yeni bağlantı")
        try:
            data = b""
            while True:
                chunk = conn.recv(1024)
                if not chunk:
                    break
                data += chunk
            
            if data:
                self.process_capacity_data(data)
        except Exception as e:
            self.logger.error(f"Müşteri bağlantısı işlenirken hata: {e}")
        finally:
            conn.close()

    def start_monitoring(self):
        self.create_plot()
        self.logger.info("Abone Monitörü Başlatılıyor")

        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server_socket:
            server_socket.bind((self.HOST, self.PORT))
            server_socket.listen(5)
            server_socket.settimeout(self.SOCKET_TIMEOUT)
            self.logger.info(f"Sunucu {self.HOST}:{self.PORT} adresinde dinliyor")

            while self.running:
                try:
                    self.check_stale_connections()
                    self.update_plot()

                    try:
                        conn, addr = server_socket.accept()
                        client_thread = threading.Thread(
                            target=self.handle_client_connection,
                            args=(conn, addr)
                        )
                        client_thread.start()
                    except socket.timeout:
                        continue

                except KeyboardInterrupt:
                    self.logger.info("Monitör kapatılıyor...")
                    self.running = False
                except Exception as e:
                    self.logger.error(f"Beklenmeyen hata: {e}")

def main():
    monitor = SubscriberMonitor()
    monitor.start_monitoring()

if __name__ == "__main__":
    main()
