package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean tmp = true;
        int count = 0;
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(path))) {
            while (tmp) {
                String input = scanner.nextLine();
                writer.println(input);
                if (input.equals(STOP)) {
                    count = 1;
                } else if (input.equals(OUT)) {
                    writer.println(readAnswersBot().get(1));
                    System.out.println(readAnswersBot().get(1));
                    tmp = false;
                } else if (input.equals(CONTINUE)) {
                    count = 0;
                    writer.println(readAnswersBot().get(0));
                    System.out.println(readAnswersBot().get(0));
                } else if (count == 0) {
                    writer.println(readAnswersBot().get(0));
                    System.out.println(readAnswersBot().get(0));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private List<String> readAnswersBot() {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            while (in.ready()) {
                list.add(in.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       return list;
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/record.txt", "data/answers.txt");
        cc.run();
    }
}
