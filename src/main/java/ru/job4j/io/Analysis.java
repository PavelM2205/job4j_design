package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Analysis {

    public void unavailable(String source, String target) {
        List<String[]> list = new ArrayList<>();
        List<String> result = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            list = read.lines()
                    .map(string -> string.split(" ", 2))
                    .toList();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        boolean available = true;
        for (var el : list) {
            boolean condition = el[0].equals("500") || el[0].equals("400");
            if (condition && available) {
                result.add(el[1]);
                result.add(";");
                available = false;
            } else if (!condition && !available) {
                result.add(el[1]);
                result.add(";");
                result.add(System.lineSeparator());
                available = true;
            }
        }
        try (PrintWriter out = new PrintWriter(new BufferedWriter(
                new FileWriter(target)))) {
            for (var el : result) {
                out.print(el);
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("./data/source.txt", "./data/target.txt");
    }
}
