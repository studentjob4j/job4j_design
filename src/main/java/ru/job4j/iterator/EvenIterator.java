package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class EvenIterator implements Iterator<Integer> {
    private final int[] numbers;
    private int count;

    public EvenIterator(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        return this.exist() == 0;
    }

    @Override
    public Integer next() {
        if (this.hasNext()) {
            return this.numbers[this.count++];
        } else {
            throw new NoSuchElementException();
        }
    }

    private Integer exist() {
        int value = -1;
        for (int index = this.count; index < this.numbers.length; index++) {
            if (this.numbers[index] % 2 == 0) {
                this.count = index;
                value++;
                break;
            }
        }
        return value;
    }
}
