package ru.job4j.io;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.io.FileInputStream;
import java.util.stream.Stream;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        valid(argsName);
        var scanner = new Scanner(new BufferedInputStream(new FileInputStream(argsName.get("path"))))
                .useDelimiter(System.lineSeparator());
        String del = argsName.get("delimiter");
        String[] stringsHead = scanner.next().split(del);
        List<String> filter = Arrays.asList(argsName.get("filter").split(","));
        List<String> tableHead = Arrays.asList(stringsHead);
        StringBuilder output = new StringBuilder();
        output.append(addLine(filter, stringsHead, tableHead)).append(System.lineSeparator());
        while (scanner.hasNext()) {
            String[] strings = scanner.next().split(del);
            output.append(addLine(filter, strings, tableHead));
            output.append(System.lineSeparator());
        }
        print(argsName, output);
    }

    private static StringBuilder addLine(List<String> filter, String[] strings, List<String> tableHead) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < filter.size(); i++) {
            line.append(strings[tableHead.indexOf(filter.get(i))]).append(";");
        }
        line.setLength(line.length() - 1);
        return line;
    }

    private static void print(ArgsName argsName, StringBuilder output) {
        if ("stdout".equals(argsName.get("out"))) {
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

    public static void main(String[] args) throws Exception {
        handle(ArgsName.of(args));
    }
}
