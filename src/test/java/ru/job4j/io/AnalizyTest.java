package ru.job4j.io;

import org.junit.Test;

public class AnalizyTest {

    @Test
    public void whenServerNotWorkSomeTime() {
        Analizy analizy = new Analizy();
        String log = "data/server_log.txt";
        String target = "";
        analizy.unavailable(log, target);
    }
}