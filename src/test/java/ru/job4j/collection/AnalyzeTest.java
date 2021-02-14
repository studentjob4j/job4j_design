package ru.job4j.collection;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalyzeTest {

    @Test
    public void whenDiffList() {
        List<Analyze.User> previous = List.of(
                new Analyze.User(1, "one"),
                new Analyze.User(2, "two"),
                new Analyze.User(3, "three"),
                new Analyze.User(4, "four"),
                new Analyze.User(5, "five")
        );

        List<Analyze.User> current = List.of(
                new Analyze.User(1, "change"),
                new Analyze.User(2, "two"),
                new Analyze.User(6, "add"),
                new Analyze.User(4, "change")
        );

        Analyze.Info info = new Analyze().diff(previous, current);
        assertThat(info.added, is(1));
        assertThat(info.changed, is(2));
        assertThat(info.deleted, is(2));
        assertThat(info.old, is(1));
    }
}