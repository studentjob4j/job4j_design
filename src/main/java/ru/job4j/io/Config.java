package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();
    private String temp;

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
           loadHelper(read);
        } catch (IllegalArgumentException e) {
            System.out.println("Было перехвачено исключение");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadHelper(BufferedReader read) throws IllegalArgumentException, IOException {
        int count = 0;
        String one = null;
        String two = null;
        temp = read.readLine();
        while (temp != null) {
            if (temp.contains("#") || temp.length() == 0 || temp.contains("//")) {
                temp = read.readLine();
                continue;
            }
            for (String tmp : temp.split("=")) {
                if (count == 0) {
                    one = tmp;
                    count++;
                } else {
                    two = tmp;
                    count = 0;
                }
            }
            if (count != 0) {
                throw new IllegalArgumentException();
            }
            values.putIfAbsent(one, two);
            temp = read.readLine();
        }
    }

    public String value(String key) {
        String result = null;
        if (key == null && values.get(key).isEmpty()) {
            throw new UnsupportedOperationException();
        } else {
            result = values.get(key);
        }
        return result;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}
