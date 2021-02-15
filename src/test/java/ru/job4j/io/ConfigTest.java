package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("John Connor"));
    }

    @Test
    public void whenWithComment() {
        String path = "data/with_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("Key"), is("Value"));
    }

    @Test
    public void whenWithoutValue() {
        String path = "data/key_without_value.properties";
        Config config = new Config(path);
        config.load();
    }
}