package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private int[][] data;
    private int row = 0;
    private int cell = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (row < data.length) {
            if (cell == data[row].length || data[row].length == 0) {
                row++;
                cell = 0;
            } else {
                break;
            }
        }
        if (row < data.length && cell < data[row].length) {
            return true;
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][cell++];
    }
}
