package ru.job4j.exam;

import ru.job4j.io.ArgsName;
import ru.job4j.io.SearchFiles;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class SearchCriterion {

    public static void main(String[] args) throws IOException  {
        ArgsName argsName = ArgsName.of(args);
        valid(argsName, args);
        SearchCriterion searchCriterion = new SearchCriterion();
        Path start = Paths.get(argsName.get("d"));
        searchCriterion.saveLog(search(start, argsName), argsName);
    }

    private static void valid(ArgsName argsName, String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("enter all parameters");
        }
        if (!Files.isDirectory(Paths.get(argsName.get("d")))) {
            throw new IllegalArgumentException("parameter is not a directory");
        }
    }

    public static List<Path> search(Path root, ArgsName argsName) throws IOException {
        Predicate<Path> condition = null;
        if ("mask".equals(argsName.get("t"))) {
            condition = p -> p.toFile().getName().endsWith(argsName.get("n").replace("*", ""));
        } else if ("name".equals(argsName.get("t"))) {
            condition = p -> p.toFile().getName().equals(argsName.get("n"));
        } else if ("regex".equals(argsName.get("t"))) {
            Pattern pattern = Pattern.compile(argsName.get("n"));
            condition = p -> (pattern.matcher(p.toFile().getName())).find();
        }
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private void saveLog(List<Path> log, ArgsName argsName) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(argsName.get("o")))) {
            for (Path path: log) {
               pw.println(path.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
