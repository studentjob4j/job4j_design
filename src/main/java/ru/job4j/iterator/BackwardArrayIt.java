package ru.job4j.iterator;

// Итератор в обратном порядке

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardArrayIt implements Iterator<Integer> {

    private final int[] data;
    private int point;

    public BackwardArrayIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int temp = data.length - point - 1;
        point++;
        return data[temp];
    }
}
