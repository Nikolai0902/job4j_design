package ru.job4j.design.srp;

import ru.job4j.design.storage.Employee;

import java.util.function.Predicate;

/**
 * Интерфейс для реализовция системы отчетов.
 */
public interface Report {
    String generate(Predicate<Employee> filter);
}
