package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Set<FileProperty> set = new HashSet<>();
    private List<FileProperty> list = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(Files.size(file), file.getFileName().toString(),
                file.toAbsolutePath().toString());
        if (!set.add(fileProperty)) {
            list.add(fileProperty);
        }
        return super.visitFile(file, attrs);
    }

    public List<FileProperty> getPaths() {
        set.retainAll(list);
        list.addAll(set);
        return list;
    }
}
