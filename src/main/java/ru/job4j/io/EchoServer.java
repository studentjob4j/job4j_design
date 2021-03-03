package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        String str = null;

        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                int count = 0;
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                     while (in.ready()) {
                         str = in.readLine();
                         if (count == 0) {
                             writeInOut(str, out, server);
                             count = 1;
                         }
                        System.out.println(str);
                     }
                }
            }
        } catch (Exception e) {
           // e.printStackTrace();
            LOG.error("Exception in streams", e);
        }
    }

    private static void writeInOut(String value, OutputStream out, ServerSocket server) throws IOException {
        List<String> list = List.of("HTTP/1.1 200 OK\r\n\r\n", "GoodBye My Dear friend\r\n\r\n", "Hello my dear friend\r\n\r\n",
                "What\r\n\r\n");
        out.write(list.get(0).getBytes());
            if (value.contains("Exit")) {
                out.write(list.get(1).getBytes());
                server.close();
            }
            if (value.contains("Hello")) {
                out.write(list.get(2).getBytes());
            }
            if (value.contains("Any")) {
                out.write(list.get(3).getBytes());
            }
    }
}
