package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterator<T> {

    private T[] array;
    private int index;
    private int count;

    public SimpleArray(T[] array) {
        this.array = array;
    }

    public void add(T value) {
         array[index++] = value;
    }

    public boolean set(T value, int position) {
        boolean result = false;
        if (Objects.checkIndex(position, index) == position) {
            array[position] = value;
            result = true;
        }
        return result;
    }

    public T get(int position) {
        T result = null;
        if (Objects.checkIndex(position, index) == position) {
            result = array[position];
        }
        return result;
    }

    public T[] remove(int position) {
        if (Objects.checkIndex(position, index) == position) {
            System.arraycopy(array, position + 1, array, position, --index);
        }
        return array;
    }

    @Override
    public boolean hasNext() {
        return count < index;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[count++];
    }
}
