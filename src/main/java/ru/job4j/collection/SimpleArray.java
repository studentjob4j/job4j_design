package ru.job4j.collection;

//Динамический список на массиве

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] container;
    private int index;
    private int modCount;

    public SimpleArray() {
        this.container = new Object[0];
    }

    public T get(int index) {
        T result = null;
        if (Objects.checkIndex(index, container.length) == index) {
           result = (T) container[index];
        }
        return result;
    }

    public void add(T model) {
        if (index == 0) {
            container = new Object[1];
            container[index++] = model;
            modCount++;
            return;
        } else if (index == container.length) {
            Object[] temp = container;
            container = Arrays.copyOf(container, container.length + 1);
            System.arraycopy(temp, 0, container, 0, temp.length);
            container[container.length - 1] = model;
            index++;
            modCount++;
            return;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return index < container.length;
            }

            @Override
            public T next() {
                T result = null;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                } else {
                    result = (T) container[index++];
                }
                return result;
            }
        };
    }
}
