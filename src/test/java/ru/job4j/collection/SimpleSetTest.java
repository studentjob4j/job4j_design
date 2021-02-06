package ru.job4j.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Test;

public class SimpleSetTest {

   private SimpleArray array = new SimpleArray<>();

    @Before
    public void whenCreateSet() {
        array.add("one");
        array.add("six");
        array.add("two");

    }

    private SimpleSet set = new SimpleSet<>(array);

    @Test
    public void whenAddDuplicateThen() {
       boolean result = set.add("six");
       assertThat(result, is(false));
   }

   @Test
    public void whenAddNull() {
      boolean result = set.add(null);
      assertThat(result, is(false));
   }
}