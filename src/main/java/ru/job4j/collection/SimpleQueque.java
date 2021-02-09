package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueque<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() throws NoSuchElementException {
     T result = null;
     while (in.getSize() > 0) {
         T value = in.pop();
         out.push(value);
     }
        result = out.getLastElement();
        return result;
    }

    public void push(T value) {
        in.push(value);
    }
}
