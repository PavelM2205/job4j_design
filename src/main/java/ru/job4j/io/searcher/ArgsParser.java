package ru.job4j.io.searcher;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsParser {
    private final Map<String, String> values = new HashMap<>();

    private void parse(String[] args) {
        Arrays.stream(args)
                .peek(this::checkPattern)
                .map(str -> str.replace("-", ""))
                .map(str -> str.split("=", 2))
                .forEach(mas -> values.put(mas[0], mas[1]));
    }

    private void checkPattern(String str) {
        if (!str.matches("-.+=.+")) {
            throw new IllegalArgumentException("Parameter doesn't match the pattern: \"-key=value\"");
        }
    }

    public String getValue(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("Key %s doesn't exist", key));
        }
        return values.get(key);
    }

    public static ArgsParser parseOf(String[] arguments) {
        ArgsParser parser = new ArgsParser();
        parser.parse(arguments);
        return parser;
    }
}
