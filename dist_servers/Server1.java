import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import com.google.protobuf.*;

public class Server1 {
    private static final int SERVER_PORT = 5001;
    private static final int OTHER_SERVER_PORT_1 = 5002;
    private static final int OTHER_SERVER_PORT_2 = 5003;
    private static final String ADMIN_HOST = "localhost";
    private static final int ADMIN_PORT = 4001;

    private static final Map<Integer, String> subscriberMap = new ConcurrentHashMap<>();
    private static final int faultToleranceLevel = 2;
    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server1 started on port: " + SERVER_PORT);

            // Start the client connection handler in a new thread
            new Thread(Server1::processClientConnections).start();

            // Start listening for updates from other servers
            new Thread(Server1::receiveServerUpdates).start();

            // Start periodically sending capacity information to admin
            new Thread(Server1::sendPeriodicCapacityUpdate).start();

        } catch (IOException e) {
            System.err.println("Error initializing server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void processClientConnections() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                handleClientRequest(clientSocket);
            } catch (IOException e) {
                System.err.println("Error accepting client connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void receiveServerUpdates() {
        try (ServerSocket updateSocket = new ServerSocket(SERVER_PORT + 1000)) { // Update listening port
            while (true) {
                try (Socket socket = updateSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",", 2);
                        if (parts.length == 2) {
                            int id = Integer.parseInt(parts[0]);
                            String name = parts[1];
                            subscriberMap.put(id, name);
                            System.out.println("Subscriber info received from another server: " + id + " - " + name);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error receiving updates from other servers: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void handleClientRequest(Socket clientSocket) {
        try (InputStream input = clientSocket.getInputStream();
             OutputStream output = clientSocket.getOutputStream()) {

            SubscriberOuterClass.Subscriber subscriber = SubscriberOuterClass.Subscriber.parseFrom(input);

            if (subscriber.getDemand() == SubscriberOuterClass.Subscriber.Demand.SUBS) {
                subscriberMap.put(subscriber.getID(), subscriber.getNameSurname());
                System.out.println("New subscriber added: " + subscriber);
                forwardSubscriberInfoToOtherServers(subscriber);
            } else if (subscriber.getDemand() == SubscriberOuterClass.Subscriber.Demand.DEL) {
                subscriberMap.remove(subscriber.getID());
                System.out.println("Subscriber removed: " + subscriber);
            }

        } catch (IOException e) {
            System.err.println("Error processing client request: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void forwardSubscriberInfoToOtherServers(SubscriberOuterClass.Subscriber subscriber) {
        String message = subscriber.getID() + "," + subscriber.getNameSurname();

        if (faultToleranceLevel >= 1) {
            sendMessageToServer(OTHER_SERVER_PORT_1 + 1000, message);
        }
        if (faultToleranceLevel == 2) {
            sendMessageToServer(OTHER_SERVER_PORT_2 + 1000, message);
        }
    }

    private static void sendMessageToServer(int port, String message) {
        try (Socket socket = new Socket("localhost", port);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            writer.println(message);

        } catch (IOException e) {
            System.err.println("Failed to send message to server on port: " + port);
            e.printStackTrace();
        }
    }

    private static void sendPeriodicCapacityUpdate() {
        while (true) {
            try {
                sendCapacityToAdmin();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.err.println("Periodic capacity update interrupted: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void sendCapacityToAdmin() {
        try (Socket socket = new Socket(ADMIN_HOST, ADMIN_PORT);
             OutputStream output = socket.getOutputStream()) {

            CapacityOuterClass.Capacity capacity = CapacityOuterClass.Capacity.newBuilder()
                    .setSubscriberCount(subscriberMap.size())
                    .build();

            capacity.writeTo(output);
            output.flush();

            System.out.println("Capacity info sent to admin: " + subscriberMap.size());

        } catch (IOException e) {
            System.err.println("Failed to send capacity info to admin: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
