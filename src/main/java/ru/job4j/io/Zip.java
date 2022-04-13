package ru.job4j.io;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private static ArgsName arguments;

    public void packFiles(List<Path> source, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            for (var el : source) {
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(el.toFile()))) {
                    zip.putNextEntry(new ZipEntry(el.toString()));
                    zip.write(in.readAllBytes());
                    zip.closeEntry();
                }
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public void packSingleFile(Path source, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target.toFile())))) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(in.readAllBytes());
                }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    private void checkArguments(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException(
                    "Arguments are invalid. Use java -jar pack.jar -d=DIRECTORY -e=EXCLUDE -o=OUTPUT");
        }
        arguments = ArgsName.of(args);
        Path path = Path.of(arguments.get("d"));
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(String.format("Not exist %s", path));
        }
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException(String.format("%s is not directory", path));
        }
        String extension = arguments.get("e");
        if (!extension.startsWith(".")) {
            throw new IllegalArgumentException(
                    String.format("Extension %s must start with \".\"", extension));
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.checkArguments(args);
        List<Path> source = Search.search(
                Path.of(arguments.get("d")), file -> !file.getFileName().toString().endsWith(arguments.get("e")));
        zip.packFiles(source, Path.of(arguments.get("o")));
    }
}
