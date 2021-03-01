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
        List<String> list = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        list = readAnswersBot();
        try (FileOutputStream writers = new FileOutputStream(path)) {
            while (tmp) {
                String input = scanner.nextLine();
                temp.add(input);
                if (input.equals(STOP)) {
                    count = 1;
                } else if (input.equals(OUT)) {
                    temp.add(list.get((int) (Math.random() * list.size())));
                    System.out.println(list.get((int) (Math.random() * list.size())));
                    tmp = false;
                } else if (input.equals(CONTINUE)) {
                    count = 0;
                    temp.add(list.get((int) (Math.random() * list.size())));
                    System.out.println(list.get((int) (Math.random() * list.size())));
                } else if (count == 0) {
                    temp.add(list.get((int) (Math.random() * list.size())));
                    System.out.println(list.get((int) (Math.random() * list.size())));
                }
            }
            recInTheFile(temp, writers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void recInTheFile(List<String> list, FileOutputStream out) {
        list.stream().forEach(x -> {
            try {
                out.write(x.getBytes());
                out.write(System.lineSeparator().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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
