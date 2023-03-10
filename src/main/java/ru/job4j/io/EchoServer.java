package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());
    private static final Map<String, Consumer<OutputStream>> MAPA = new HashMap<>();

    public static void main(String[] args) {
        String str = null;
        try (ServerSocket server = new ServerSocket(9000)) {
            writeDataInMap(server);
            while (!server.isClosed()) {
                int count = 0;
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                     while (in.ready()) {
                         String temp = in.readLine();
                         if (count == 0) {
                             String value = splitArgument(temp);
                             writeInOut(value, out, server);
                             count = 1;
                         }
                        System.out.println(temp);
                     }
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in streams", e);
        }
    }

    private static void writeDataInMap(ServerSocket server) {
        MAPA.put("Hello", x -> {
            try {
                x.write("Hello my dear friend\r\n\r\n".getBytes());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        MAPA.put("Any", x -> {
            try {
                x.write("What\r\n\r\n".getBytes());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        MAPA.put("Exit", x -> {
            try {
                x.write("GoodBye My Dear friend\r\n\r\n".getBytes());
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static String splitArgument(String value) {
       String[] temp = value.split("=");
       String[] tmp = temp[1].split(" ");
        return tmp[0];
    }

    private static void writeInOut(String value, OutputStream out, ServerSocket server) throws IOException {
        out.write("HTTP/1.1 200 OK\\r\\n\\r\\n".getBytes());
        out.write(System.lineSeparator().getBytes());
        // ?????????? ???????????????????? ???????????????? ???? ?????????? ?? ???????? ?????? ???????????????? ?????????? ???????????????????? ???????????????? ???? ??????????????????
        MAPA.getOrDefault(value, outputStream -> {
            try {
                outputStream.write("I Don't understand\r\n\r\n".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).accept(out);
    }
}
