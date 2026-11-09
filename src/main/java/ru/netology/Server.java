package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер стартовал");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true, StandardCharsets.UTF_8);
                     BufferedReader in = new BufferedReader(new InputStreamReader(
                             clientSocket.getInputStream(), StandardCharsets.UTF_8))) {
                    talk(clientSocket, in, out);
                } catch (IOException e) {
                    System.err.println("Ошибка соединения: " + e.getMessage());
                }
            }
        }
    }

    private static void talk(Socket clientSocket, BufferedReader in, PrintWriter out) throws IOException {
        String userName;
        out.println("Write your name");
        while (true) {
            userName = in.readLine();
            if (userName == null) {
                return;
            }
            if (!userName.isBlank()) {
                userName = userName.trim();
                break;
            }
            out.println("Name cannot be empty. Write your name");
        }
        System.out.println("Порт клиента: " + clientSocket.getPort() + " имя: " + userName);

        String answer;
        out.println("Are you child? (yes/no)");
        while (true) {
            answer = in.readLine();
            if (answer == null) {
                return;
            }
            answer = answer.trim();
            if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no")) {
                break;
            }
            out.println("Wrong answer. Are you child? (yes/no)");
        }

        if (answer.equalsIgnoreCase("yes")) {
            out.println("Welcome to the kids area, " + userName + "! Let's play!");
        } else {
            out.println("Welcome to the adult zone, " + userName
                    + "! Have a good rest, or a good working day!");
        }
    }
}
