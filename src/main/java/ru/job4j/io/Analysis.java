package ru.job4j.io;

import java.io.*;

public class Analysis {

    private boolean available = true;

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(target)))) {
            read.lines()
                    .map(string -> string.split(" ", 2))
                    .map(this::checkString)
                    .forEach(out::print);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    private String checkString(String[] massive) {
        String result = "";
            boolean condition = "500".equals(massive[0]) || "400".equals(massive[0]);
            if (condition && available) {
                result = massive[1] + ";";
                available = false;
            } else if (!condition && !available) {
                result = massive[1] + ";" + System.lineSeparator();
                available = true;
            }
        return result;
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("./data/source.txt", "./data/target.txt");
    }
}
