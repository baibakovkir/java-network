package ru.netology;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер стартовал");
            while (true) {
                try (
                        Socket clientSocket = serverSocket.accept();
                        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                        ) {
                    String infoFromClient = bufferedReader.readLine();
                    System.out.println("Порт клиента: " + clientSocket.getPort() + " данные от клиента: " + infoFromClient);
                    printWriter.println(clientSocket.getPort());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
