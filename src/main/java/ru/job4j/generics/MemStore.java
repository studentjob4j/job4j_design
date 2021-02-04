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
        T temp = null;
        int tmp = searchIndex(id);
            if (tmp == -1) {
                return false;
            }
        temp = mem.set(tmp, model);
        return temp.getId().equals(id);
    }

    @Override
    public boolean delete(String id) {
            T temp = null;
            int tmp = searchIndex(id);
            if (tmp == -1) {
                return false;
            }
        temp = mem.remove(tmp);
        return (temp.getId().equals(id));
    }

    @Override
    public T findById(String id) {
            T temp = null;
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
