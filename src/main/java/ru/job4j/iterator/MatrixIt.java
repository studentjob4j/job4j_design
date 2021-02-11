package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {

    private int[][] array;
    private int row;
    private int col;

    public MatrixIt(int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        while (row < array.length && array[row].length == col) {
            row++;
            col = 0;
        }
        return row < array.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[row][col++];
    }
}
