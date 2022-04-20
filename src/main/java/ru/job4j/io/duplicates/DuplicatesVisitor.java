package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static java.nio.file.FileVisitResult.CONTINUE;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        List<Path> pathList = new ArrayList<>();
        pathList.add(file);
        if (files.containsKey(fileProperty)) {
            files.get(fileProperty).add(file);
        }
        files.putIfAbsent(fileProperty, pathList);
        return CONTINUE;
    }

    public void resultFile() {
        for (List<Path> value : files.values()) {
            if (value.size() >= 2) {
                for (Path path: value) {
                    System.out.println(path.toFile().getAbsoluteFile());
                }
            }
        }
    }
}
