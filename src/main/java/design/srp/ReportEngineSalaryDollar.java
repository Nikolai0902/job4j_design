package design.srp;

import java.util.function.Predicate;
import static design.srp.ReportEngine.DATE_FORMAT;

/**
 * Система отчетов для отдела бугалтерии в долларах(Изменен вид зарплаты).
 */
public class ReportEngineSalaryDollar implements Report {

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
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append("Американский доллар: ").append(employee.getSalary() / 50).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
