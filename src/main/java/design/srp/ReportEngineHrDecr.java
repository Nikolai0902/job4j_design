package design.srp;

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
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
