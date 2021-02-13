package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {

    public static List<String> filter(String file) {
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("log.txt"))) {
            result = findRows(in.lines().collect(Collectors.toList()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static List<String> findRows(List<String> list) {
        List<String> rsl = new ArrayList<>();
        for (String temp : list) {
            for (String tmp : temp.split(" ")) {
                if (tmp.equals("404")) {
                    rsl.add(temp);
                    break;
                }
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        Iterator<String> it = log.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
