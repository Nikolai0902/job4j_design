package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        List<Path> f = new ArrayList<>();
        f.add(file);
        if (files.containsKey(fileProperty)) {
            files.get(fileProperty).add(file);
        }
        files.putIfAbsent(fileProperty, f);
        return super.visitFile(file, attrs);
    }

    public Map<FileProperty, List<Path>> result() {
        return files;
    }
}
