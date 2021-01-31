package ru.job4j.generics;

//универсальное хранилище

import java.util.ArrayList;
import java.util.List;

public class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        T temp = mem.set(searchIndex(id), model);
        return temp.getId().equals(id);
    }

    @Override
    public boolean delete(String id) {
        T temp = mem.remove(searchIndex(id));
        return (temp.getId().equals(id));
    }

    @Override
    public T findById(String id) {
        T result = mem.get(searchIndex(id));
        return result;
    }

    private int searchIndex(String id) {
        int result = -1;
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }
}
