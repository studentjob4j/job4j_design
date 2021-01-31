package ru.job4j.generics;

public class UserStore implements Store<User> {
    private final Store<User> store = new MemStore<>();

    @Override
    public void add(User model) {
       store.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
       boolean result = store.replace(id, model);
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = store.delete(id);
        return result;
    }

    @Override
    public User findById(String id) {
        return store.findById(id);
    }
}
