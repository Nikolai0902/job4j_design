package design.srp;

import java.util.function.Predicate;

/**
 * Система отчетов для отдела программистов в HTML.
 */
public class ReportEngineHTML implements Report {

    private Store store;

    public ReportEngineHTML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append(String.format("<%s>", "Name, Hired, Fired, Salary"))
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(String.format("<%s>", employee.getName()))
                    .append(String.format("<%s>", employee.getHired().getTime()))
                    .append(String.format("<%s>", employee.getFired().getTime()))
                    .append(String.format("<%s>", employee.getSalary()))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
