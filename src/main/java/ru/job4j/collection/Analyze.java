package ru.job4j.collection;

import java.util.*;

public class Analyze {

    private int old;
    private int add;
    private int delete;
    private int change;

    public Info diff(List<User> previous, List<User> current) {
       Map<Integer, User> map = new HashMap<>();
       for (User temp : current) {
           map.put(temp.id, temp);
       }
       for (User temp : previous) {
           if (map.containsKey(temp.id) && map.get(temp.id).name.equals(temp.name)) {
              old++;
           } else if (map.containsKey(temp.id) && !map.get(temp.id).name.equals(temp.name)) {
               change++;
           } else if (!map.containsKey(temp.id)) {
               delete++;
           }
       }
       add = current.size() - old - change;
       return new Info(add, change, delete, old);
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

    }

    public static class Info {
        int added;
        int changed;
        int deleted;
        int old;

        public Info(int added, int changed, int deleted, int old) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
            this.old = old;
        }
    }
}
