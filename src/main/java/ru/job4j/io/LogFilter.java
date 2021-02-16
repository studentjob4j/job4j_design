package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LogFilter {

    public static List<String> filter(String file) {
        List<String> result = new ArrayList<>();
        String temp = null;
        try (BufferedReader in = new BufferedReader(new FileReader("log.txt"))) {
            temp = in.readLine();
            while (temp != null) {
                String value = findRows(temp);
                if (value == null) {
                    temp = in.readLine();
                    continue;
                }
                    result.add(value);
                    temp = in.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String findRows(String value) {
            String result = null;
            for (String tmp : value.split(" ")) {
                if (tmp.equals("404")) {
                    result = value;
                    break;
                }
            }
        return result;
    }

    public static void save(List<String> log, String file) {
        int count = 0;
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            while (log.size() > count) {
                String temp = log.get(count++);
                out.write(temp + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        Iterator<String> it = log.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        List<String> log2 = filter("log.txt");
        save(log2, "404.txt");
    }
}
