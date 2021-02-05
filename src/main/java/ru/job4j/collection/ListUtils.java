package ru.job4j.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Predicate;

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
        ListIterator<T> it = list.listIterator();
        List<T> result = new ArrayList<>();
        while (it.hasNext()) {
            T temp = it.next();
            if (filter.test(temp)) {
                result.add(temp);
            }
        }
        return result;
    }

    public static <T> List<T> replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            T temp = it.next();
            if (filter.test(temp)) {
                it.set(value);
            }
        }
        return list;
    }

    public static <T> List<T> removeAll(List<T> list, List<T> elements) {
        ListIterator<T> major = list.listIterator();
        ListIterator<T> slave = elements.listIterator();
        while (major.hasNext()) {
            T one = major.next();
            while (slave.hasNext()) {
                if (one.equals(slave.next())) {
                    int index = major.nextIndex();
                    list.remove(--index);
                    major = list.listIterator();
                    break;
                }
            }
        }
        return list;
    }
}
