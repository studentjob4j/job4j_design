package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Search  extends SimpleFileVisitor<Path> {

    private List<Path> list = new ArrayList<>();
    private Predicate<String> predicate;

    public Search(Predicate<String> predicate) {
        this.predicate = predicate;
    }

    public Search() {
    }

    public List<Path> getList() {
        return list;
    }
    // метод ищет в файловой системе файлы которые отвечают предикату
    public List<Path> search(Path root, String ext) throws IOException {
        predicate = (x -> x.endsWith(ext));
        Search searcher = new Search(predicate);
        Files.walkFileTree(root, searcher);
        return searcher.getList();
    }

    @Override
    public  FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path temp = file.getFileName();
        if (predicate.test(temp.toString())) {
        list.add(temp);
        }
        return FileVisitResult.CONTINUE;
    }
}
