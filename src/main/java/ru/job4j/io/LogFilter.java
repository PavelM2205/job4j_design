package ru.job4j.io;

import java.io.*;
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

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (var el : log) {
                out.println(el);
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        save(log, "404.txt");
    }
}
