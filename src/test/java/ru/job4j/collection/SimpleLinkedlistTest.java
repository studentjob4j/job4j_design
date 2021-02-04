package ru.job4j.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SimpleLinkedlistTest {

  private SimpleLinkedlist<Integer> list = new SimpleLinkedlist();

    @Before
    public void beforeTest() {
        list.add(1);
        list.add(5);
        list.add(7);
    }

  @Test
  public void whenAddNodeAndGetNodeByIndex() {
      assertThat(list.get(0), is(1));
      assertThat(list.get(1), is(5));
      assertThat(list.get(2), is(7));
  }

    @Test
    public void whenCheckIterator() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenModCountItFromIteratorNotEqualModCount() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.next(), is(1));
        list.add(4);
        it.next();
    }

    @Test
    public void whenAddOneElementThen() {
        SimpleLinkedlist<Integer> simple = new SimpleLinkedlist<>();
        simple.add(1);
        Iterator<Integer> it = simple.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(false));
    }
}