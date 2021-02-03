package ru.job4j.generics;

//универсальное хранилище

import java.util.ArrayList;
import java.util.List;

public class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();
    private T temp;

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int tmp = searchIndex(id);
            if (tmp != -1) {
                temp = mem.set(tmp, model);
            } else {
                return false;
            }
        return temp.getId().equals(id);
    }

    @Override
    public boolean delete(String id) {
            int tmp = searchIndex(id);
            if (tmp != -1) {
                temp = mem.remove(tmp);
            } else {
                return false;
            }
        return (temp.getId().equals(id));
    }

    @Override
    public T findById(String id) {
            int tmp = searchIndex(id);
            if (tmp != -1) {
                temp = mem.get(tmp);
            }
        return temp;
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
