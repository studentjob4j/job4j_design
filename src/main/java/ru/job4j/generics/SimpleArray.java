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
        try {
            if (Objects.checkIndex(position, index) == position) {
                array[position] = value;
                result = true;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Введите правильный индекс");
        }
        return result;
    }

    public T get(int position) {
        T result = null;
        try {
            if (Objects.checkIndex(position, index) == position) {
                result = array[position];
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Введите правильный индекс");
        }

        return result;
    }

    public T[] remove(int position) {
        try {
            if (Objects.checkIndex(position, index) == position) {
                System.arraycopy(array, position + 1, array, position, --index);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Введите правильный индекс");
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
