package ru.job4j.solid.ocp;

import ru.job4j.question.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Класс осуществляет сортировку списка.
 * Данный метод нарушает принцип OCP(типы методов и полей).
 * Параметры и возвращаемые типы методов и полей привязаны к конкретным
 *	реалзациям сущностей (ArrayList и Employee).
 */
public class Class4 {
    private ArrayList<User> target;

    public ArrayList<User> sortDsc(ArrayList<User> value, Comparator<User> comparator) {
        return sort(value, comparator.reversed());
    }
    public ArrayList<User> sortAsc(ArrayList<User> value, Comparator<User> comparator) {
        return sort(value, comparator);
    }

    private ArrayList<User> sort(ArrayList<User> value, Comparator<User> comparator) {
        target = new ArrayList<>(value);
        target.sort(comparator);
        return target;
    }
}
