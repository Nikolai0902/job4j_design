package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        toString().lines()
                .filter(k -> !k.startsWith(" ") || !k.endsWith(" "))
                .filter(l -> !l.isEmpty())
                .filter(l -> !l.startsWith("#"))
                .filter(s -> {
                    if (!s.contains("=") || s.startsWith("=") || s.endsWith("=")) {
                        throw new IllegalArgumentException("Ошибка данных");
                    }
                    return true;
                })
                .map(n -> n.split("=", 2))
                .forEach(r -> values.put(r[0], r[1]));
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("app.properties");
        config.load();
        System.out.println(config.value("hibernate.dialect"));
    }

}
