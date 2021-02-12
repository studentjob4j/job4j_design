package ru.job4j.collection;

import java.util.List;

public class Analyze {

    private int old;
    private int add;
    private int delete;
    private int change;

    public Info diff(List<User> previous, List<User> current) {
        for (User temp : previous) {
            for (User tmp : current) {
                if (temp.id == tmp.id) {
                    if (temp.name.equals(tmp.name)) {
                        old++;
                    } else {
                        change++;
                    }
                }
            }
        }
        add = current.size() - old - change;
        delete = previous.size() - old - change - add;
        return new Info(add, change, delete);
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

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }
    }
}
