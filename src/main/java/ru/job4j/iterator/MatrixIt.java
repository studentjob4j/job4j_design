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
        int locRow = row;
        boolean result = false;
        int count = 0;
        while (array[locRow].length == 0) {
            locRow++;
            count++;
            if (count == array.length) {
                return false;
            }
        }
        if (array[locRow].length != 0) {
            result = true;
            locRow--;
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (array[row].length == 0) {
            while (array[row].length == 0) {
                row++;
                col = 0;
            }
        }
        Integer result = array[row][col++];
        if (col >= array[row].length) {
            row++;
            col = 0;
        }
        return result;
    }
}
