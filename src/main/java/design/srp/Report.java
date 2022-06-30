package design.srp;

import java.util.function.Predicate;

/**
 * Интерфейс для реализовция системы отчетов.
 */
public interface Report {
    String generate(Predicate<Employee> filter);
}
