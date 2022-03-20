package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class SimpleLinkedListTest {

    @Test
    public void whenAddAndGet() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(2), list.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetFromOutOfBoundThenExceptionThrown() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.get(2);
    }

    @Test
    public void whenAddIteratorHasNextTrue() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void whenAddIteratorNextOne() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> iterator = list.iterator();
        assertEquals(Integer.valueOf(1), iterator.next());
    }

    @Test
    public void whenEmptyThenIteratorHasNextFalse() {
        List<Integer> list = new SimpleLinkedList<>();
        Iterator<Integer> iterator = list.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenAddIteratorMultiHasNextTrue() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
    }

    @Test
    public void whenAddIteratorNextOneNextTwo() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        assertEquals(Integer.valueOf(1), iterator.next());
        assertEquals(Integer.valueOf(2), iterator.next());
    }

    @Test
    public void whenGetIteratorTwiceThenEveryFromBegin() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> first = list.iterator();
        assertTrue(first.hasNext());
        assertEquals(Integer.valueOf(1), first.next());
        assertTrue(first.hasNext());
        assertEquals(Integer.valueOf(2), first.next());
        assertFalse(first.hasNext());
        Iterator<Integer> second = list.iterator();
        assertTrue(second.hasNext());
        assertEquals(Integer.valueOf(1), second.next());
        assertTrue(second.hasNext());
        assertEquals(Integer.valueOf(2), second.next());
        assertFalse(second.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        List<Integer> list = new SimpleLinkedList<>();
        Iterator<Integer> iterator = list.iterator();
        list.add(1);
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyThenIteratorNextMustThrowException() {
        List<Integer> list = new SimpleLinkedList<>();
        Iterator<Integer> iterator = list.iterator();
        iterator.next();
    }
}