package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class OutputMatrix {

    public static int[][] multiple(int size) {
        int[][] result = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int cell = 0; cell < size; cell++) {
                result[row][cell] = (row + 1) * (cell + 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] mas = multiple(6);
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 0; i < mas.length; i++) {
                for (int j = 0; j < mas.length; j++) {
                    out.write(String.valueOf(mas[i][j]).getBytes());
                    out.write(" ".getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}

