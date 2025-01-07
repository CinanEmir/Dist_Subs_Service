import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import com.google.protobuf.*;

public class Server2 {
    private static final int SERVER_PORT = 5002;
    private static final int CONNECTED_SERVER_PORT_1 = 5001;
    private static final int CONNECTED_SERVER_PORT_2 = 5003;
    private static final String ADMIN_HOST = "localhost";
    private static final int ADMIN_PORT = 4002;

    private static final Map<Integer, String> subscriberRegistry = new ConcurrentHashMap<>();
    private static final int faultToleranceThreshold = 2;
    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server2 is active on port: " + SERVER_PORT);

            // Thread: Handle client subscription requests
            new Thread(Server2::acceptClientConnections).start();

            // Thread: Handle updates from other servers
            new Thread(Server2::processUpdatesFromServers).start();

            // Thread: Periodically send server capacity info to admin
            new Thread(Server2::sendPeriodicCapacityUpdates).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void acceptClientConnections() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                processClientRequest(clientSocket);
            } catch (IOException e) {
                System.err.println("Error: Unable to accept client connection.");
                e.printStackTrace();
            }
        }
    }

    private static void processUpdatesFromServers() {
        while (true) {
            try (ServerSocket updateServerSocket = new ServerSocket(SERVER_PORT + 1000)) { // Listening port for updates
                while (true) {
                    try (Socket socket = updateServerSocket.accept();
                         BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                        String line;
                        while ((line = reader.readLine()) != null) {
                            String[] parts = line.split(",", 2);
                            if (parts.length == 2) {
                                int id = Integer.parseInt(parts[0]);
                                String name = parts[1];
                                subscriberRegistry.put(id, name);
                                System.out.println("Subscriber data received from another server: " + id + " - " + name);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Error receiving updates from other servers: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void processClientRequest(Socket clientSocket) {
        try (InputStream input = clientSocket.getInputStream();
             OutputStream output = clientSocket.getOutputStream()) {

            SubscriberOuterClass.Subscriber subscriber = SubscriberOuterClass.Subscriber.parseFrom(input);

            if (subscriber.getDemand() == SubscriberOuterClass.Subscriber.Demand.SUBS) {
                subscriberRegistry.put(subscriber.getID(), subscriber.getNameSurname());
                System.out.println("New subscriber added: " + subscriber);
                forwardSubscriberToOtherServers(subscriber);

            } else if (subscriber.getDemand() == SubscriberOuterClass.Subscriber.Demand.DEL) {
                subscriberRegistry.remove(subscriber.getID());
                System.out.println("Subscriber removed: " + subscriber);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void forwardSubscriberToOtherServers(SubscriberOuterClass.Subscriber subscriber) {
        String message = subscriber.getID() + "," + subscriber.getNameSurname();

        if (faultToleranceThreshold >= 1) {
            sendToServer(CONNECTED_SERVER_PORT_1 + 1000, message);
        }
        if (faultToleranceThreshold == 2) {
            sendToServer(CONNECTED_SERVER_PORT_2 + 1000, message);
        }
    }

    private static void sendToServer(int port, String message) {
        try (Socket socket = new Socket("localhost", port);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            writer.println(message);

        } catch (IOException e) {
            System.err.println("Failed to send message to server on port: " + port);
            e.printStackTrace();
        }
    }

    private static void sendPeriodicCapacityUpdates() {
        while (true) {
            try {
                sendCapacityInfoToAdmin();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.err.println("Error: Periodic capacity update interrupted.");
                e.printStackTrace();
            }
        }
    }

    private static void sendCapacityInfoToAdmin() {
        try (Socket socket = new Socket(ADMIN_HOST, ADMIN_PORT);
             OutputStream output = socket.getOutputStream()) {

            CapacityOuterClass.Capacity capacity = CapacityOuterClass.Capacity.newBuilder()
                    .setSubscriberCount(subscriberRegistry.size())
                    .build();

            capacity.writeTo(output);
            output.flush();

            System.out.println("Capacity info sent to admin: " + subscriberRegistry.size());

        } catch (IOException e) {
            System.err.println("Failed to send capacity info to admin.");
            e.printStackTrace();
        }
    }
}
