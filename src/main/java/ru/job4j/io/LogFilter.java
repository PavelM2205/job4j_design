package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {

    public List<String> filter(String file) {
        List<String> result = null;
        try (FileReader reader = new FileReader(file);
        BufferedReader in = new BufferedReader(reader)) {
            result = in.lines()
                    .filter(string -> string.contains(" 404 "))
                    .toList();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        for (var el : log) {
            System.out.println(el);
        }
    }
}
