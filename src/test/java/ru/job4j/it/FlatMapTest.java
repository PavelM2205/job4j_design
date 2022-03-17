package ru.job4j.it;

import org.junit.Test;
import org.junit.runners.model.InvalidTestClassError;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class FlatMapTest {

    @Test
    public void whenDiffNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator(),
                List.of(2, 3).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertEquals(Integer.valueOf(1), flat.next());
        assertEquals(Integer.valueOf(2), flat.next());
        assertEquals(Integer.valueOf(3), flat.next());
    }

    @Test
    public void whenSeqNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertEquals(Integer.valueOf(1), flat.next());
        assertEquals(Integer.valueOf(2), flat.next());
        assertEquals(Integer.valueOf(3), flat.next());
    }

    @Test
    public void whenMultiHasNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertTrue(flat.hasNext());
        assertTrue(flat.hasNext());
    }

    @Test
    public void whenHasNextFalse() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertEquals(Integer.valueOf(1), flat.next());
        assertFalse(flat.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmpty() {
        Iterator<Iterator<Object>> data = List.of(
                List.of().iterator()
        ).iterator();
        FlatMap<Object> flat = new FlatMap<>(data);
        flat.next();
    }

    @Test
    public void whenSeveralEmptyAndNotEmpty() {
        Iterator<Iterator<?>> data = List.of(
                List.of().iterator(),
                List.of().iterator(),
                List.of().iterator(),
                List.of(1).iterator()
        ).iterator();
        FlatMap flat = new FlatMap(data);
        assertTrue(flat.hasNext());
        assertEquals(1, flat.next());
    }

    @Test
    public void whenSeveralEmptyThenReturnFalse() {
        Iterator<Iterator<Object>> data = List.of(
                List.of().iterator(),
                List.of().iterator(),
                List.of().iterator(),
                List.of().iterator()
        ).iterator();
        FlatMap flat = new FlatMap(data);
        assertFalse(flat.hasNext());
    }
}