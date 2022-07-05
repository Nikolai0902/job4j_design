package ru.job4j.design.sorting;

import ru.job4j.design.storage.Employee;

import java.util.Comparator;

public class SortEmployeeSalary implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return Integer.compare((int) o2.getSalary(), (int) o1.getSalary());
    }
}
