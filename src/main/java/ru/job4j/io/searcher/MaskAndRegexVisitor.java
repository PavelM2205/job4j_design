package ru.job4j.io.searcher;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class MaskAndRegexVisitor extends SimpleFileVisitor<Path> {

    private final String name;
    private final String type;
    private PathMatcher matcher;
    private final List<String> paths = new ArrayList<>();

    public MaskAndRegexVisitor(String name, String type) {
        this.name = name;
        this.type = type;
        setMatcher();
    }

    public List<String> getPaths() {
        return paths;
    }

    private void setMatcher() {
        if ("mask".equals(type)) {
            matcher = FileSystems.getDefault().getPathMatcher(String.format("glob:%s", name));
        } else {
            matcher = FileSystems.getDefault().getPathMatcher(String.format("%s:%s", type, name));
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (matcher.matches(file.getFileName())) {
            paths.add(file.toString());
        }
        return super.visitFile(file, attrs);
    }
}
