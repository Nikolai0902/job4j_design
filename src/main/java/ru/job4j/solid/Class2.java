package ru.job4j.solid;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Нарушение принципа SRP.
 * Класс умеет их создавать и инициализировать.
 * Должен быть конструктор с инициализацией дирекктории.
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
}
