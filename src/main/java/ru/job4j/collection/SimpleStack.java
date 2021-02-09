package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();
    private int size;

    public T pop() {
        size--;
        return linked.deleteLast();
    }

    public void push(T value) {
         linked.add(value);
         size++;
    }

    public int getSize() {
        return size;
    }

    public T getLastElement() throws NoSuchElementException {
        size--;
        return linked.deleteLast();
    }

}
