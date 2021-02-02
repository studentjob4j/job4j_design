package ru.job4j.generics;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class SimpleArrayTest {

    private Integer[] array = new Integer[10];

    @Test
    public void whenAdd() {
        SimpleArray test = new SimpleArray(array);
        test.add(1);
        assertThat(test.get(0), is(1));
    }

    @Test
    public void whenSet() {
        SimpleArray test = new SimpleArray(array);
        test.add(2);
        test.add(4);
        boolean result = test.set(6, 1);
        assertThat(result, is(true));
    }

    @Test
    public void whenRemove() {
        SimpleArray test = new SimpleArray(array);
        test.add(2);
        test.add(4);
        test.add(6);
        test.remove(1);
        assertThat(test.iterator().hasNext(), is(true));
        assertThat(test.iterator().next(), is(2));
        assertThat(test.iterator().hasNext(), is(true));
        assertThat(test.iterator().next(), is(6));
        assertThat(test.iterator().hasNext(), is(false));
    }
}