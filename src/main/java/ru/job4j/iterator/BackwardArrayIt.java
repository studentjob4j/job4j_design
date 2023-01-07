package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Что такое итератор
 * @author Shegai Evgenii
 * @version 1.0
 * @since 07.01.2023
 */

public class BackwardArrayIt implements Iterator<Integer> {

    private final int[] data;
    private int point = 0;

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
        int index = data.length - point - 1;
        point++;
        return data[index];
    }
}
