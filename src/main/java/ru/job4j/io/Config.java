package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    private void checkPattern(String string) {
        if (!string.matches(".+=.+")) {
            throw new IllegalArgumentException("File doesn't match the pattern: \"key=value\"");
        }
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(string -> string.length() != 0 && !string.startsWith("#"))
                    .peek(this::checkPattern)
                    .map(string -> string.split("=", 2))
                    .forEach(strmas -> values.put(strmas[0], strmas[1]));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    public int getSize() {
        return values.size();
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return out.toString();
    }
}
