package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .message()
                .isNotEmpty()
                .startsWith("col");

    }

    @Test
    public void when() {
        NameLoad load = new NameLoad();
        load.parse("one=two", "six=seven");
        Map<String, String> map = load.getMap();
        Set<Map.Entry<String, String>> set = map.entrySet();
        set.stream().forEach(System.out::println);
    }

    @Test
    public void whenArrayLengthIsZero() {
        NameLoad load = new NameLoad();
        assertThatThrownBy(() -> load.parse()).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    public void whenStringDontContainEquals() {
        NameLoad load = new NameLoad();
        String name = "password";
        assertThatThrownBy(() -> load.parse(name)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("this name: %s does not contain the symbol \"=\"", name));
    }

    @Test
    public void whenStringStartWithEquals() {
        NameLoad load = new NameLoad();
        String name = "=password";
        assertThatThrownBy(() -> load.parse(name)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("this name: %s does not contain a key", name));
    }

    @Test
    public void whenStringEndWithEquals() {
        NameLoad load = new NameLoad();
        String name = "password=";
        assertThatThrownBy(() -> load.parse(name)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("this name: %s does not contain a value", name));
    }
}