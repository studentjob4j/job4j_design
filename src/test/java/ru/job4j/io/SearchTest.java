package ru.job4j.io;

import org.junit.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SearchTest {

    @Test
    public void whenGetFileWhoEndJs() throws IOException {
        Search search = new Search();
        Path start = Paths.get(".");
        List<Path> list = search.search(start, "txt");
        assertThat(list.size(), is(34));
    }


    @Test
    public void whenNoValidParametrDirectory() {
        Search search = new Search();
        String[] array = new String[2];
        array[0] = null;
        array[1] = "txt";
       assertThat(search.valid(array), is(false));
    }

    @Test
    public void whenNoValidParametrEndWith() {
        Search search = new Search();
        String[] array = new String[2];
        array[0] = ".";
        array[1] = null;
        assertThat(search.valid(array), is(false));
    }

    @Test
    public void whenParametrOnlyOne() {
        Search search = new Search();
        String[] array = new String[1];
        array[0] = ".";
        assertThat(search.valid(array), is(false));
    }

    @Test
    public void whenAllParametrsValid() {
        Search search = new Search();
        String[] array = new String[2];
        array[0] = ".";
        array[1] = "txt";
        assertThat(search.valid(array), is(true));
    }
}