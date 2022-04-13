package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("Key %s isn't exist", key));
        }
        return values.get(key);
    }

    private void checkPattern(String string) {
        if (!string.matches("-.+=.+")) {
            throw new IllegalArgumentException("Parameter doesn't match the pattern \"-key=value\"");
        }
    }

    private void parse(String[] args) {
        Arrays.stream(args)
                .peek(this::checkPattern)
                .map(string -> string.replace("-", ""))
                .map(string -> string.split("=", 2))
                .forEach(massive -> values.put(massive[0], massive[1]));
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
