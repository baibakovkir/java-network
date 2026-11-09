package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("netology.homework", Server.PORT);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true, StandardCharsets.UTF_8);
             BufferedReader in = new BufferedReader(new InputStreamReader(
                     clientSocket.getInputStream(), StandardCharsets.UTF_8))) {
            Scanner scanner = new Scanner(System.in);
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(message);
                if (message.startsWith("Welcome to")) {
                    break;
                }
                if (!scanner.hasNextLine()) {
                    break;
                }
                out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            System.err.println("Ошибка соединения: " + e.getMessage());
        }
    }
}
