package ru.job4j.collection;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class SimpleQueueTest {

    @Test
    public void whenPushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        assertEquals(Integer.valueOf(1), queue.poll());
    }

    @Test
    public void when2PushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        assertEquals(Integer.valueOf(1), queue.poll());
    }

    @Test
    public void when2PushPollPushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.poll();
        queue.push(2);
        assertEquals(Integer.valueOf(2), queue.poll());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.poll();
    }

    @Test
    public void whenPushPushPollAndPush() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.poll();
        queue.push(3);
        assertEquals(Integer.valueOf(2), queue.poll());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyThenMustBeException() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.poll();
    }
}