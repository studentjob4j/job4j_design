package ru.job4j.collection;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
             if (i.previousIndex() == index) {
                i.add(value);
             }
             i.next();
        }
    }

    public static <T> List<T> removeIf(List<T> list, Predicate<T> filter) {
        return list.stream().filter(filter).collect(Collectors.toList());
    }

    public static <T> List<T> replaceIf(List<T> list, Predicate<T> filter, T value) {
        return list.stream().filter(filter).map(x -> value).collect(Collectors.toList());
    }

    public static <T> List<T> removeAll(List<T> list, List<T> elements) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < elements.size(); j++) {
                if (list.get(i) == elements.get(j)) {
                    list.remove(i);
                }
            }
        }
        return list;
    }
}
