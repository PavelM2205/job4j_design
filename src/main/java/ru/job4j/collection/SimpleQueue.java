package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int count = 0;

    public T poll() {
        if (in.size() == 0 && out.size() == 0) {
            throw new  NoSuchElementException();
        }
        if (out.size() == 0) {
            while (count > 0) {
                out.push(in.pop());
                count--;
            }
        }
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        count++;
    }
}
