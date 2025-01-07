import java.io.*;
import java.net.*;
import java.util.Scanner;

import com.google.protobuf.*;

public class SubscriptionClient1 {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5002;

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            System.out.println("Sunucuya başarıyla bağlanıldı: " + SERVER_ADDRESS + ":" + SERVER_PORT);
            InputStream socketInput = clientSocket.getInputStream();
            OutputStream socketOutput = clientSocket.getOutputStream();

            System.out.println("Abone ol (A) ya da aboneliği iptal et (B):");
            Scanner scanner = new Scanner(System.in);
            String userChoice = scanner.nextLine();

            if (userChoice.equalsIgnoreCase("A")) {
                // Abonelik talebi gönder
                SubscriberOuterClass.Subscriber subscriptionRequest = SubscriberOuterClass.Subscriber.newBuilder()
                        .setID(102)
                        .setNameSurname("Emir Deniz")
                        .setDemand(SubscriberOuterClass.Subscriber.Demand.SUBS)
                        .build();
                subscriptionRequest.writeTo(socketOutput);
                socketOutput.flush();
                System.out.println("Abonelik talebi başarıyla gönderildi: " + subscriptionRequest);
            } else if (userChoice.equalsIgnoreCase("B")) {
                // Abonelik iptali talebi gönder
                SubscriberOuterClass.Subscriber unsubscribeRequest = SubscriberOuterClass.Subscriber.newBuilder()
                        .setID(102)
                        .setDemand(SubscriberOuterClass.Subscriber.Demand.DEL)
                        .build();
                unsubscribeRequest.writeTo(socketOutput);
                socketOutput.flush();
                System.out.println("Abonelik iptal talebi başarıyla gönderildi: " + unsubscribeRequest);
            } else {
                System.out.println("Geçersiz seçim. Lütfen 'A' veya 'B' giriniz.");
            }
        } catch (IOException ex) {
            System.out.println("Sunucuya bağlantı sağlanamadı: " + SERVER_ADDRESS + ":" + SERVER_PORT);
            ex.printStackTrace();
        }
    }
}
