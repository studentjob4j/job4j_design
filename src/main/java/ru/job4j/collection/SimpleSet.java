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
       if  (Objects.nonNull(value)) {
           Iterator<T> it = array.iterator();
           while (it.hasNext()) {
               if (value.equals(it.next())) {
                   result = true;
                   break;
               }
           }
           if (!result) {
               array.add(value);
           }
       }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Iterator<T> it = array.iterator();
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public T next() {
                if (!it.hasNext()) {
                    throw new NoSuchElementException();
                }
                return it.next();
            }
        };
    }
}
