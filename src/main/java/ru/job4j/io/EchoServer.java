package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        boolean closeable;
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String firstLine = in.readLine();
                    closeable = firstLine.matches(".+msg=Bye.+");
                    System.out.println(firstLine);
                    for (String str = in.readLine(); str != null && !str.isEmpty();
                         str = in.readLine()) {
                        System.out.println(str);
                    }
                    out.flush();
                }
                if (closeable) {
                    server.close();
                }
            }
        }
    }
}
