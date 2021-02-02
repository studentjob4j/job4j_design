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
        T result = null;
        Objects.checkIndex(pos, index);
        return (T) container[pos];
    }

    public void add(T model) {
        if (index == 0) {
            container[index++] = model;
            modCount++;
            return;
        } else {
            container[index++] = model;
            modCount++;
        }
        if (index == container.length - 1) {
            //Object[] temp = container;
            container = Arrays.copyOf(container, container.length * 2);
           // System.arraycopy(temp, 0, container, 0, temp.length);
            container[findIndex()] = model;
            index++;
            modCount++;
            return;
        }
    }

    private int findIndex() {
        int result = -1;
        for (int i = 0; i < container.length; i++) {
            if (container[i] == null) {
                result = i;
                break;
            }
        }
        return result;
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
}
