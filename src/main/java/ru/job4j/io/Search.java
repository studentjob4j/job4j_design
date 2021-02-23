package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class Search {

    public List<Path> search(Path value, String temp) throws IOException {
        SearchFiles searcher = new SearchFiles(x -> x.toFile().getName().endsWith(temp));
        Files.walkFileTree(value, searcher);
        return searcher.getList();
    }

    public List<Path> search2(Path value, String temp) throws IOException {
        SearchFiles searcher = new SearchFiles(x -> !x.toFile().getName().endsWith(temp));
        Files.walkFileTree(value, searcher);
        return searcher.getList();
    }

    public static void main(String[] args) throws IOException {
        if (!valid(args)) {
            throw new IllegalArgumentException("Wrong arguments");
        }
        SearchFiles searcher = new SearchFiles(x -> x.toFile().getName().endsWith(args[1]));
        Files.walkFileTree(Paths.get(args[0]), searcher);
        searcher.getList()
                .stream()
                .forEach(x -> System.out.println(x.toFile().getName()));
    }

    public static boolean valid(String[] values) {
        boolean rsl = false;
        if (values.length != 2) {
            return rsl;
        } else if (values[0] != null && values[1] != null) {
           rsl = true;
        }
        return rsl;
    }
}
