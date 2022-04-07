package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private int port = 9999;

    public Server() {
    }

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        System.out.println("===СЕРВЕР ЗАПУЩЕН===");
        while (!isInterrupted()){
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                Socket socket = serverSocket.accept();

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println("Connected in " + socket.getInetAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("клиент: " + in.readLine());

                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
