package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Ошибка данных");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String string : args) {
            if (string.split("=").length < 2) {
                throw new IllegalArgumentException("Ошибка данных");
            }
            String[] splitStr = Arrays.stream(string.split("([-=])", 3))
                    .filter(str -> !str.isEmpty()).toArray(String[]::new);
            values.put(splitStr[0], splitStr[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
