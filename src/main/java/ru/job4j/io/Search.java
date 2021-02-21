package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class Search {

    public List<Path> search(Path value, String temp) throws IOException {
        SearchFiles searcher = new SearchFiles(x -> x.endsWith(temp));
        Files.walkFileTree(value, searcher);
        return searcher.getList();
    }

    public static void main(String[] args) throws IOException {
        SearchFiles searcher = new SearchFiles(x -> x.endsWith(args[1]));
        Files.walkFileTree(Paths.get(args[0]), searcher);
        searcher.getList().stream().forEach(x -> System.out.println(x.toFile().getName()));
    }
}
