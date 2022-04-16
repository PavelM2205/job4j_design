package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {

    private static ArgsName argsName;
    private static StringJoiner joiner;
    private static StringJoiner joinerLine = new StringJoiner(System.lineSeparator());
    private static final String STDOUT = "stdout";

    public static void handle() throws IOException {
        List<String> values = new ArrayList<>();
        String[] firstString = null;
        try (Scanner scanner = new Scanner(new BufferedReader(
                new FileReader(argsName.get("path")))).useDelimiter(
                        argsName.get("delimiter") + "|" + System.lineSeparator())) {
            if (scanner.hasNextLine()) {
                firstString = scanner.nextLine().split(argsName.get("delimiter"));
            }
            while (scanner.hasNext()) {
                values.add(scanner.next());
            }
        }
        List<Integer> index = getIndex(firstString);
        int changer = 0;
        joiner = new StringJoiner(";");
        while (changer < values.size()) {
            for (var el : index) {
                joiner.add(values.get(el + changer));
            }
            joinerLine.add(joiner.toString());
            joiner = new StringJoiner(";");
            changer += firstString.length;
        }
        printResult();
    }

    private static List<Integer> getIndex(String[] firstLine) {
        joiner = new StringJoiner(";");
        if (firstLine == null) {
            throw new IllegalArgumentException("Empty file");
        }
        List<Integer> indexes = new ArrayList<>();
        String[] filter = argsName.get("filter").split(",");
        for (var el : filter) {
            for (int i = 0; i < firstLine.length; i++) {
                if (firstLine[i].equals(el)) {
                    indexes.add(i);
                    joiner.add(firstLine[i]);
                }
            }
        }
        joinerLine.add(joiner.toString());
        return indexes;
    }

    private static void printResult() throws IOException {
        String target = argsName.get("out");
        if (STDOUT.equals(target)) {
            System.out.println(joinerLine);
        } else {
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(target)))) {
                out.println(joinerLine);
            }
        }
    }

    private static void checkArguments(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException(
                    "Arguments are invalid. Use java -jar csvReader.jar -path=PATH"
                            + " -delimiter=DELIMITER -out=OUT -filter=FILTER");
        }
        argsName = ArgsName.of(args);
        Path path = Path.of(argsName.get("path"));
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(String.format("%s doesn't exist", path));
        }
    }

    public static void main(String[] args) throws IOException {
        checkArguments(args);
        CSVReader.handle();
    }
}
