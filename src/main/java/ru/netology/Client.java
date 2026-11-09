package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("localhost", Server.PORT);
             PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            printWriter.println("Hello from client");
            String serverResponse = bufferedReader.readLine();
            System.out.println("Server response: " + serverResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
