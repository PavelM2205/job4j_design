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

    public void load() {
        List<String[]> list = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            list = read.lines()
                    .filter(string -> (!string.contains("#")) && string.length() != 0)
                    .map(string -> string.split("="))
                    .toList();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        for (var el : list) {
            if (el[0].length() == 0 || el[1].length() == 0) {
                throw new IllegalArgumentException();
            }
            values.put(el[0], el[1]);
        }
    }

    public String value(String key) {
        return values.get(key);
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
