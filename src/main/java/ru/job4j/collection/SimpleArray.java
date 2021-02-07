package ru.job4j.collection;

//Динамический список на массиве

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] container;
    private int index;
    private int modCount;

    public SimpleArray() {
        this.container = new Object[10];
    }

    public T get(int pos) {
        Objects.checkIndex(pos, index);
        return (T) container[pos];
    }

    public void add(T model) {
        if (index == container.length) {
            expansionArray();
        }
        container[index++] = model;
        modCount++;
    }

    private void expansionArray() {
        container = Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int position;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return position < index;
            }

            @Override
            public T next() {
                T result = null;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                } else {
                    result = (T) container[position++];
                }
                return result;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleArray<?> that = (SimpleArray<?>) o;
        return Arrays.equals(container, that.container);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(container);
    }
}
