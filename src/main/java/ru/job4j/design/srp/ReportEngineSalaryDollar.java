package ru.job4j.design.srp;

import ru.job4j.design.storage.Employee;
import ru.job4j.design.storage.Store;

import java.util.function.Predicate;

/**
 * Система отчетов для отдела бугалтерии в долларах(Изменен вид зарплаты).
 */
public class ReportEngineSalaryDollar implements Report {

    public static final int COURSE = 50;

    private Store store;

    public ReportEngineSalaryDollar(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(ReportEngine.DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(ReportEngine.DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append("Американский доллар: ").append(employee.getSalary() / COURSE).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
