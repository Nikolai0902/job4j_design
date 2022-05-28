package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        valid(argsName);
        String del = argsName.get("delimiter");
        StringBuilder output = new StringBuilder();
        List<Integer> indexFilter = searchIndex(argsName);
        if (argsName.get("out").equals("stdout")) {
            indexFilter = List.of(0, 1);
        }
        var scanner = new Scanner(new BufferedInputStream(new FileInputStream(argsName.get("path"))))
                .useDelimiter(System.lineSeparator());
        while (scanner.hasNext()) {
            String[] split = scanner.next().split(del);
            for (int indexF = 0; indexF < indexFilter.size(); indexF++) {
                for (int indexSplit = 0; indexSplit < split.length; indexSplit++) {
                    if (indexFilter.get(indexF) == indexSplit) {
                        output.append(split[indexSplit]).append(";");
                    }
                }
            }
            output.setLength(output.length() - 1);
            output.append(System.lineSeparator());
        }
        print(argsName, output);
    }

    private static List<Integer> searchIndex(ArgsName argsName) {
        String[] filter = argsName.get("filter").split(",");
        List<Integer> indexFilter = new ArrayList<>();
            for (String value : filter) {
                if (value.equals("name")) {
                    indexFilter.add(0);
                }
                if (value.equals("age")) {
                    indexFilter.add(1);
                }
                if (value.equals("last_name")) {
                    indexFilter.add(2);
                }
                if (value.equals("education")) {
                    indexFilter.add(3);
                }
            }
        return indexFilter;
    }

    private static void print(ArgsName argsName, StringBuilder output) {
        if (argsName.get("out").equals("stdout")) {
            System.out.println(output);
        } else {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(argsName.get("out")))) {
                bw.write(output.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void valid(ArgsName args) {
        File csvFile = new File(args.get("path"));
        if (!csvFile.exists() || csvFile.isDirectory()) {
            throw new IllegalArgumentException("csv file either not exists or is directory");
        }
        if (!";".equals(args.get("delimiter"))) {
            throw new IllegalArgumentException("delimiter invalid values");
        }
    }
}
