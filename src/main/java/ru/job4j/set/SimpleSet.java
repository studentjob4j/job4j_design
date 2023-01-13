package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 * SimpleSet
 * @author Shegai Evgenii
 * @version 1.0
 * @since 13.01.2023
 */

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(10);

    @Override
    public boolean add(T value) {
        if (!contains(value)) {
            set.add(value);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T value) {
        boolean result = false;
        Iterator<T> iterator = set.iterator();
        while (iterator.hasNext()) {
            if (Objects.equals(value, iterator.next())) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
