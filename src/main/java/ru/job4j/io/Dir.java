package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("/Users/pavel/IdeaProjects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exists %s",
                    file.getAbsolutePath()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s",
                    file.getAbsolutePath()));
        }
        for (File subfile : file.listFiles()) {
            System.out.printf("%s : %d%n", subfile.getName(), subfile.length());
        }
    }
}
