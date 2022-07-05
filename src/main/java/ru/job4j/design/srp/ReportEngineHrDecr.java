package ru.job4j.design.srp;

import ru.job4j.design.sorting.SortEmployeeSalary;
import ru.job4j.design.storage.Employee;
import ru.job4j.design.storage.Store;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * Ситсема отчетов для отдела HR (Отсутствуют даты).
 */
public class ReportEngineHrDecr implements Report {

    private Store store;

    public ReportEngineHrDecr(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        List<Employee> employeeList = store.findBy(filter);
        Collections.sort(employeeList, new SortEmployeeSalary());
        for (Employee employee : employeeList) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
