package ru.job4j.io.searcher;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class NameVisitor extends SimpleFileVisitor<Path> {
    private final String name;
    private final List<String> paths = new ArrayList<>();

    public NameVisitor(String name) {
        this.name = name;
    }

    public List<String> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.endsWith(name)) {
            paths.add(file.toString());
        }
        return super.visitFile(file, attrs);
    }
}
