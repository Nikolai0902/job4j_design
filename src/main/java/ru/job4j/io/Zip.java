package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path file : sources) {
                zip.putNextEntry(new ZipEntry(file.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void valid(ArgsName argsName) {
        if (!Files.isDirectory(Paths.get(argsName.get("d")))) {
            throw new IllegalArgumentException("parameter is not a dirrectory");
        }
        if (!argsName.get("e").startsWith(".class")) {
            throw new IllegalArgumentException("incorrect file extension");
        }
        if (!Files.exists(Paths.get(argsName.get("o")))) {
            throw new IllegalArgumentException("zip file with this name does not exist");
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("enter all parameters");
        }
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        valid(argsName);
        Path root = Paths.get(argsName.get("d"));
        zip.packFiles((Search.search(root, p -> !p.toFile()
                .getName()
                .endsWith(argsName.get("e")))),
                new File(argsName.get("o")));
    }
}
