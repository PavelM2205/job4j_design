package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutThenGetValueIsNotNull() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        assertEquals("one", map.get(1));
    }

    @Test
    public void whenEmptyThenNull() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertNull(map.get(1));
    }

    @Test
    public void whenGetForInvalidKeyThenNull() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        assertNull(map.get(2));
    }

    @Test
    public void whenPutForSameKeyThenFalse() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        assertFalse(map.put(1, "two"));
    }

    @Test
    public void whenMultiPutThenGetIsValid() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");
        map.put(7, "seven");
        map.put(8, "eight");
        map.put(9, "nine");
        assertEquals("one", map.get(1));
        assertEquals("two", map.get(2));
        assertEquals("three", map.get(3));
        assertEquals("four", map.get(4));
        assertEquals("five", map.get(5));
        assertEquals("six", map.get(6));
        assertEquals("seven", map.get(7));
        assertEquals("eight", map.get(8));
        assertEquals("nine", map.get(9));
    }

    @Test
    public void whenRemoveThenNull() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.remove(1);
        assertNull(map.get(1));
    }

    @Test
    public void whenEmptyMapAndRemoveThenFalse() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertFalse(map.remove(1));
    }

    @Test
    public void whenRemoveForInvalidKeyThenFalse() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        assertFalse(map.remove(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnAllElements() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        Iterator<Integer> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(1), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(2), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(3), iterator.next());
        assertFalse(iterator.hasNext());
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        Iterator<Integer> iterator = map.iterator();
        map.put(2, "two");
        iterator.next();
    }

    @Test
    public void whenEmptyThenIteratorHasNextFalse() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        Iterator<Integer> iterator = map.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenAddThenIteratorMultiHasNextTrue() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        Iterator<Integer> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
    }
}