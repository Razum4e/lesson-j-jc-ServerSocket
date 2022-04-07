package Server;

import java.io.*;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Server server = new Server(9999);
        server.start();

        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            Socket socket = new Socket("localhost", 9999);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("сервер: " + in.readLine());

            Thread.sleep(1000);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("привет, сервер");

            out.close();
            in.close();
        }
        server.interrupt();
    }
}
