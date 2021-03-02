package ru.job4j.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        String str = null;
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                     while (in.ready()) {
                         str = in.readLine();
                         if (str.contains("Exit")) {
                             out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                             out.write("GoodBye My Dear friend\r\n\r\n".getBytes());
                             server.close();
                         } else if (str.contains("Hello")) {
                             out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                             out.write("Hello my dear friend\r\n\r\n".getBytes());
                             break;
                         } else if (str.contains("Any")) {
                             out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                             out.write("What\r\n\r\n".getBytes());
                             break;
                         } else {
                             out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                             break;
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
}
