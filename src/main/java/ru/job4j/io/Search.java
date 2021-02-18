package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class Search {

    // метод ищет в файловой системе файлы которые отвечают предикату

    public List<Path> search(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(x -> x.endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getList();
    }
}
