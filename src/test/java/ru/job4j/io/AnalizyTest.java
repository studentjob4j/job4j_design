package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Test
    public void whenServerNotWorkSomeTime() {
        Analizy analizy = new Analizy();
        String log = "data/server_log.txt";
        String target = "";
        analizy.unavailable(log, target);
        String expect = "10:57:01;10:59:01" + System.lineSeparator() + "11:01:02;11:02:02" + System.lineSeparator();
        String temp = "";
        try (BufferedReader read = new BufferedReader(new FileReader("data/target.txt"))) {
            while (read.ready()) {
                temp += read.readLine() + System.lineSeparator();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(temp, is(expect));
    }
}