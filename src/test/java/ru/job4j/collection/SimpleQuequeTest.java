package ru.job4j.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.NoSuchElementException;

public class SimpleQuequeTest {

    @Test
    public void whenPushPoll() {
        SimpleQueque<Integer> queue = new SimpleQueque<>();
        queue.push(1);
        int rsl = queue.poll();
        assertThat(rsl, is(1));
    }

    @Test
    public void when2PushPoll() {
        SimpleQueque<Integer> queue = new SimpleQueque<>();
        queue.push(1);
        queue.push(2);
        int rsl = queue.poll();
        assertThat(rsl, is(1));
    }

    @Test
    public void when2PushPollPushPoll() {
        SimpleQueque<Integer> queue = new SimpleQueque<>();
        queue.push(1);
        queue.poll();
        queue.push(2);
        int rsl = queue.poll();
        assertThat(rsl, is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyPoll() {
        SimpleQueque<Integer> queue = new SimpleQueque<>();
        queue.poll();
    }
    @Test
    public void whenPushPushPollAndPush() {
        SimpleQueque<Integer> queue = new SimpleQueque<>();
        queue.push(1);
        queue.push(2);
        queue.poll();
        queue.push(3);
        queue.poll();
        assertThat(queue.poll(), is(2));
    }

}