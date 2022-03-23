package ru.job4j.iterator;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertEquals(Arrays.asList(1, 2, 3), input);
    }

    @Test
    public void whenAddBeforeFirstElement() {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 3));
        ListUtils.addBefore(input, 0, 1);
        assertEquals(Arrays.asList(1, 2, 3), input);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 0, 2);
        assertEquals(Arrays.asList(1, 2, 3), input);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2));
        ListUtils.addAfter(input, 1, 3);
        assertEquals(Arrays.asList(1, 2, 3), input);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 3, 2);
    }

    @Test
    public void whenRemoveEvenNumbers() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Predicate<Integer> filter = (value) -> value % 2 == 0;
        ListUtils.removeIf(input, filter);
        assertEquals(Arrays.asList(1, 3, 5), input);
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Predicate<Integer> filter = (value) -> value % 2 != 0;
        ListUtils.replaceIf(input, filter, 8);
        assertEquals(List.of(8, 2, 8, 4, 8, 6), input);
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> removed = List.of(2, 3, 4);
        ListUtils.removeAll(input, removed);
        assertEquals(List.of(1, 5, 6), input);
    }

    @Test
    public void whenDuplicateAndRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 3, 4, 4, 5, 6));
        List<Integer> removed = List.of(2, 3, 4);
        ListUtils.removeAll(input, removed);
        assertEquals(List.of(1, 5, 6), input);
    }
}