package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

/**
 * create Store<T extends Base>
 * @author Shegai Evgenii
 * @version 1.0
 * @since 09.01.2023
 */

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        return storage.replace(id, model) != null;
    }

    @Override
    public boolean delete(String id) {
        return storage.remove(id, findById(id));
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}

