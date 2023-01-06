package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    public void whenGetSphereThen() {
        Box box = new Box(0, 1);
        String result = box.whatsThis();
        assertThat(result)
                .contains("p")
                .startsWith("S")
                .isEqualTo("Sphere");
    }

    @Test
    public void whenGetNotSphereThen() {
        Box box = new Box(0, 0);
        String result = box.whatsThis();
        assertThat(result).isNotEmpty()
                .endsWith("t")
                .isEqualTo("Unknown object");
    }

    @Test
    public void whenGetNumberVerticesThen() {
        Box box = new Box(4, 4);
        int result = box.getNumberOfVertices();
        assertThat(result)
                .isPositive()
                .isGreaterThan(0)
                .isEqualTo(4);
    }

    @Test
    public void whenNumberVerticesMore8Then() {
        Box box = new Box(9, 4);
        int result = box.getNumberOfVertices();
        String value = box.whatsThis();
        assertThat(result).isEqualTo(-1);
        assertThat(value).isNotEmpty().isEqualTo("Unknown object");
    }

    @Test
    public void whenFigureExistThen() {
        Box box = new Box(8, 12);
        boolean result = box.isExist();
        assertThat(result).isNotNull().isTrue();
    }

    @Test
    public void whenFigureNotExistThen() {
        Box box = new Box(80, 12);
        boolean result = box.isExist();
        assertThat(result).isNotNull().isFalse();
    }

    @Test
    public void whenGetSquareThen() {
        Box box = new Box(0, 1);
        double result = box.getArea();
        assertThat(result).isNotNull()
                .isGreaterThan(3.0)
                .isGreaterThanOrEqualTo(12.56);
    }

    @Test
    public void whenGet0SquareThen() {
        Box box = new Box(-1, 1);
        double result = box.getArea();
        assertThat(result).isNotNull()
                .isLessThan(0.1).isEqualTo(0.0);
    }





}