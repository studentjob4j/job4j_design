package ru.job4j.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.Iterator;

public class SimpleHashMapTest {

    @Test
    public void whenAddElement() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(12, "value");
        assertThat(map.get(12), is("value"));
    }

    @Test
    public void whenDeleteElement() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(12, "one");
        map.insert(1, "two");
        map.delete(1);
        assertThat(map.get(12), is("one"));
        assertThat(map.getSize(), is(1));
    }

    @Test
    public void whenCreateIteratorThen() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(10, "one");
        map.insert(20, "two");
        Iterator<String> it = map.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("two"));
        assertThat(it.hasNext(), is(true));
        System.out.println(it.hasNext());
        assertThat(it.next(), is("one"));
        assertThat(it.hasNext(), is(false));
    }
}