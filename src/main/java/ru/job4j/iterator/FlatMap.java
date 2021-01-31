package ru.job4j.iterator;

//Итератор для вложенных итераторов

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class FlatMap<T>  implements Iterator<T> {

    private final Iterator<Iterator<T>> data;
    private T tmp;
    private Iterator<T> temp;
    private int count;
    private T result;

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return data.hasNext() || temp.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
       tmp = support();
        return tmp;
    }

    private T support() {
        if (count == 0) {
            temp = data.next();
            count++;
            result = temp.next();
        } else if (temp.hasNext()) {
            result = temp.next();
        } else if (!temp.hasNext()) {
            temp = data.next();
            result = temp.next();
        }
        return result;
    }

    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        while (flat.hasNext()) {
            System.out.println(flat.next());
        }
    }
}
