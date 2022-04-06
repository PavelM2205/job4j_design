package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            int read;
            StringBuilder string = new StringBuilder();
            while ((read = in.read()) != -1) {
                string.append((char) read);
            }
            String[] result = string.toString().split(System.lineSeparator());
            for (var str : result) {
                Integer number = Integer.parseInt(str);
                System.out.println("Число " + number + " является четным: "
                + (number % 2 == 0));
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}
