package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    public void whenGetList() {
        SimpleConvert convert = new SimpleConvert();
        List<String> list = convert.toList("second", "six", "s");
        assertThat(list).doesNotContainNull()
                .allSatisfy(s -> {
                    assertThat(s).containsIgnoringCase("S");
                    assertThat(s).startsWithIgnoringCase("S");
                }).anyMatch(p -> p.equals("six"))
                .noneMatch(p -> p.equals("seven"));
    }

    @Test
    public void whenGetSetByFilter() {
        SimpleConvert convert = new SimpleConvert();
        Set<String> set = convert.toSet("second", "six", "s");
        assertThat(set).filteredOn(p -> p.length() > 1).hasSize(2).contains("second", "six");
    }

    @Test
    public void whenGetMap() {
        SimpleConvert convert = new SimpleConvert();
        Map<String, Integer> map = convert.toMap("1", "2", "3", "1");
        assertThat(map).hasSize(3)
                .containsKey("1")
                .containsValue(0)
                .doesNotContainKey("four")
                .doesNotContainValue(10)
                .containsEntry("1", 0);
    }
}