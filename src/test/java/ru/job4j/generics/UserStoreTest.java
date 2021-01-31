package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserStoreTest {
    private UserStore store = new UserStore();

    @Before
    public void whenCreateData() {
        store.add(new User("google"));
        store.add(new User("yandex"));
        store.add(new User("mailru"));
    }

    @Test
    public void whenReplace() {
       boolean result = store.replace("google", new User("facebook"));
       assertThat(result, is(true));
    }

    @Test
    public void whenDelete() {
        boolean result = store.delete("yandex");
        assertThat(result, is(true));
    }

    @Test
    public void whenFind() {
        User result = store.findById("mailru");
        assertThat(result.getId(), is("mailru"));
    }
}