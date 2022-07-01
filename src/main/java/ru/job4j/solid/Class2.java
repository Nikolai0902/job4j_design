package ru.job4j.solid;

import ru.job4j.collection.list.List;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Нарушение принципа SRP.
 * Модель данных с лишней логикой.
 */
public class Class2 {
    protected String load(String key) {
        String result = null;
        try {
            result = Files.readString(Path.of(key));
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    public void print(List<String> list) {
        for (String listString : list) {
           System.out.println(listString);
        }
    }
}
