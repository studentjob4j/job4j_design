package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private T[] array;
    private int index;

    public SimpleArray(T[] array) {
        this.array = array;
    }

    public void add(T value) {
         array[index++] = value;
    }

    public boolean set(T value, int position) throws IndexOutOfBoundsException {
        boolean result = false;
            if (Objects.checkIndex(position, index) == position) {
                array[position] = value;
                result = true;
            }
        return result;
    }

    public T get(int position) throws IndexOutOfBoundsException {
        T result = null;
            if (Objects.checkIndex(position, index) == position) {
                result = array[position];
            }
        return result;
    }

    public void remove(int position) throws IndexOutOfBoundsException {
        if (Objects.checkIndex(position, index) == position) {
            System.arraycopy(array, position + 1, array, position, --index);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int count;
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
        };
    }
}
