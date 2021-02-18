package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Test
    public void whenServerNotWorkSomeTime() {
        Analizy analizy = new Analizy();
        String log = "data/server_log.txt";
        String target = "";
        analizy.unavailable(log, target);
        List<String> expect = new ArrayList<>();
        expect.add("10:57:01;10:59:01" + System.lineSeparator());
        expect.add("11:01:02;11:02:02" + System.lineSeparator());
        List<String> result = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader("data/target.txt"))) {
            while (read.ready()) {
                result.add(read.readLine() + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(result, is(expect));
    }
}