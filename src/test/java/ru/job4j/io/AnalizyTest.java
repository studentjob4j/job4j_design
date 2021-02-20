package ru.job4j.io;

import org.junit.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import org.junit.rules.TemporaryFolder;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    //считывает данные из указанного файла и записывает их в файл таргет тхт в данный момент не работате т.к
    // изменил запись во временную папку во втором тесте в коде метода unavailable
    @Ignore
    @Test
    public void whenServerNotWorkSomeTime() {
        Analizy analizy = new Analizy();
        String log = "data/server_log.txt";
        String target = "";
        analizy.unavailable(log, target);
        List<String> result = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader("data/target.txt"))) {
            while (read.ready()) {
                result.add(read.readLine() + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> expect = new ArrayList<>();
        expect.add("10:57:01;10:59:01" + System.lineSeparator());
        expect.add("11:01:02;11:02:02" + System.lineSeparator());
        assertThat(result, is(expect));
    }

    @Test
    public void whenUseTemporaryFolder() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source);
             BufferedReader in = new BufferedReader(new FileReader("data/server_log.txt"))) {
            while (in.ready()) {
                out.write(in.read());
            }
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(x -> rsl.append(x + System.lineSeparator()));
        }
        StringBuilder builder = new StringBuilder();
        builder.append("10:57:01;10:59:01" + System.lineSeparator());
        builder.append("11:01:02;11:02:02" + System.lineSeparator());
        assertThat(rsl.toString(), is(builder.toString()));
    }
}
