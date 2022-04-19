package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static boolean closeable = false;

    public static void main(String[] args) throws IOException {
        String answer;
        String firstLine;
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    firstLine = in.readLine();
                    answer = getAnswer(firstLine);
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (!closeable) {
                        out.write(answer.getBytes());
                    }
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

    private static String getAnswer(String string) {
        String result;
        if (string.matches(".+msg=Hello.+")) {
            result = "Hello, dear friend!";
        } else if (string.matches(".+msg=Exit.+")) {
            closeable = true;
            result = "";
        } else {
            int start = string.indexOf("=") + 1;
            int end = string.lastIndexOf(" ");
            result = string.substring(start, end);
        }
        return result;
    }
}
