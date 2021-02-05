package ru.job4j.collection;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar(2000, 5, 21);
        User one = new User("Natasha", 2, calendar);
        User two = new User("Natasha", 2, calendar);
        Map<User, Object> map = new HashMap<>();
        map.put(one, 1);
        map.put(two, 2);
        for (Map.Entry entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: "
                    + entry.getValue());
        }
    }
}
