package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {

    private int size;
    private int position = 0;
    private int row = 0;
    private int col = 0;
    private int[][] matrix;
    private int element;

    public MatrixIt(int[][] matrix) {
        this.matrix = matrix;
        this.size = countElements(matrix);
    }

    private int countElements(int[][] matrix) {
        int count = 0;
        for (int[] row : matrix) {
            count += row.length;
        }
        return count;
    }

    @Override
    public boolean hasNext() {
        return position < size;
    }

    @Override
    public Integer next() {
        if (position >= size) {
            throw new NoSuchElementException();
        }
        try {
            element = matrix[row][col];
        } catch (IndexOutOfBoundsException e) {
           element = matrix[++row][col];
           row--;
        }
        position++;
        col++;
        check();
        return element;
    }

    private void check() {
        while (row < matrix.length && col >= matrix[row].length) { //для того, чтоб пропустить возможные "пустые" строки
            col = 0;
            row++;
        }
    }
}
