package ru.job4j.io.searcher;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class Searcher {
    private static final String MASK = "mask";
    private static final String NAME = "name";
    private static final String REGEX = "regex";
    private static List<String> paths;
    private static ArgsParser parser;

    private static void checkArguments(String[] arguments) {
        if (arguments.length != 4) {
            throw new IllegalArgumentException(
                    "Use java -jar find.jar -d=DIRECTORY -n=FILE_NAME -t=SEARCH_TYPE -o=TARGET_FILE");
        }
        parser = ArgsParser.parseOf(arguments);
        Path source = Path.of(parser.getValue("d"));
        if (!Files.exists(source)) {
            throw new IllegalArgumentException(String.format("Directory %s doesn't exist", source));
        }
        if (!Files.isDirectory(source)) {
            throw new IllegalArgumentException(String.format("%s isn't a directory", source));
        }
        String type = parser.getValue("t");
        if (!MASK.equals(type) && !NAME.equals(type) && !REGEX.equals(type)) {
            throw new IllegalArgumentException("Key -t can be only \"mask\" or \"name\" or \"regex\"");
        }
    }

    private static void search(String directory, String type, String name) throws IOException {
        Path path = Path.of(directory);
        if (MASK.equals(type) || REGEX.equals(type)) {
            MaskAndRegexVisitor visitor = new MaskAndRegexVisitor(name, type);
            Files.walkFileTree(path, visitor);
            paths = visitor.getPaths();
        } else {
            NameVisitor visitor = new NameVisitor(name);
            Files.walkFileTree(path, visitor);
            paths = visitor.getPaths();
        }
    }

    private static void printResult(String file) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            for (var el : paths) {
                out.println(el);
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        checkArguments(args);
        search(parser.getValue("d"), parser.getValue("t"), parser.getValue("n"));
        printResult(parser.getValue("o"));
    }
}
