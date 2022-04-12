package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicateSearcher = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("."), duplicateSearcher);
        for (var el : duplicateSearcher.getPaths()) {
            System.out.println(el);
        }
    }
}
