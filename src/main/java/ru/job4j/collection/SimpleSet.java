package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleSet<T> implements Iterable<T> {

    private SimpleArray array = new SimpleArray();

    public SimpleSet(SimpleArray array) {
        this.array = array;
    }

    public boolean add(T value) {
        boolean result = false;
           if (!contains(value) && value != null) {
               array.add(value);
               result = true;
           }
        return result;
    }

    private boolean contains(T value) {
        Iterator<T> it = array.iterator();
        while (it.hasNext()) {
            if (Objects.equals(value, it.next())) {
                return true;
            }
        }
        return false;
    }


    @Override
    public Iterator<T> iterator() {
        return array.iterator();
    }
}
