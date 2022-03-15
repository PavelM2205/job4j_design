package ru.job4j.it;

import org.junit.Ignore;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class MatrixItTest {

    @Test
    public void whenOneEl() {
        int[][] in = {
                {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals(Integer.valueOf(1), it.next());
    }

    @Test
    public void whenFirstEmptyThenNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals(Integer.valueOf(1), it.next());
    }

    @Test
    public void whenFirstEmptyThenHasNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertTrue(it.hasNext());
    }

    @Test
    public void whenRowHasDifferSize() {
        int[][] in = {
                {1}, {2, 3}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals(Integer.valueOf(1), it.next());
        assertEquals(Integer.valueOf(2), it.next());
        assertEquals(Integer.valueOf(3), it.next());
    }

    @Test
    public void whenFewEmpty() {
        int[][] in = {
                {1}, {}, {}, {}, {2}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals(Integer.valueOf(1), it.next());
        assertEquals(Integer.valueOf(2), it.next());
    }

    @Test
    public void whenEmpty() {
        int[][] in = {
                {}
        };
        MatrixIt it = new MatrixIt(in);
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyThenNext() {
        int[][] in = {
                {}
        };
        MatrixIt it = new MatrixIt(in);
        it.next();
    }

    @Test
    public void whenMultiHasNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
    }

    @Test
    public void whenNoElements() {
        int[][] in = {
                {}, {}, {}
        };
        MatrixIt it = new MatrixIt(in);
        assertFalse(it.hasNext());
    }
}