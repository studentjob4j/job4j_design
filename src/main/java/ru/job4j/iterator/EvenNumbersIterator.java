package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор четных чисел
 * @Shegai Evgenii
 * @version 1.0
 * @since 08.01.2023
 */

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return this.even() == 0;

    }

    private int even() {
        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                index = i;
                return 0;
            }
        }
        return 1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];

    }
}
