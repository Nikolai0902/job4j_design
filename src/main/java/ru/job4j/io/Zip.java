package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<File> searchFile(Path filePath, String exclude) {
        List<Path> listPaths = new ArrayList<>();
        try {
            listPaths = Search.search(filePath,
                    p -> !p.toFile()
                            .getName()
                            .endsWith(exclude));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<File> listFiles = new ArrayList<>();
        for (Path path : listPaths) {
            listFiles.add(path.toFile());
        }
        return listFiles;
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Zip zip = new Zip();
        if (args.length != 3) {
            throw new IllegalArgumentException(
                    "Data error");
        }
        ArgsName argsName = ArgsName.of(args);
        Path root = Paths.get(argsName.get("d"));
        zip.packFiles(searchFile(root, argsName.get("e")),
                new File(argsName.get("o")));
    }
}
