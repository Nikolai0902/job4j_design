package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Path.of("C:\\projects"), new DuplicatesVisitor());
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Map<FileProperty, List<Path>> map = duplicatesVisitor.result();
        System.out.println(map.size());
        for (List<Path> file : map.values()) {
            if (file.size() >= 2) {
                for (Path path: file) {
                    System.out.println(path.toFile().getAbsoluteFile());
                }
            }
        }
    }
}
