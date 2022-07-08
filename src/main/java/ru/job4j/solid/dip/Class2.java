package ru.job4j.solid.dip;

import java.util.HashMap;
import java.util.Map;

/**
 * Наркшен принцип DIP.
 * Модули верхнего уровня зависят от модулей нижнего уровня.
 * Класс с хранением ДЗ зависим от реализации , а не абстракции.
 */
public class Class2 {

    Map<User, String> homeWork = new HashMap<>();

    public boolean add() {
        return false;
    }

    public String get(User user) {
        return homeWork.get(user);
    }
}

class User {
    int id;
    String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
