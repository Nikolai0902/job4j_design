package design.srp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Достпуп к базе данных добавленный для отдела HR.
 * Employee по убыванию зарплат - sorted(Comparator.naturalOrder().
 */
public class MemStoreSortedHr implements Store {

    private final List<Employee> employees = new ArrayList<>();

    @Override
    public void add(Employee em) {
        employees.add(em);
    }

    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return employees.stream().sorted(Comparator.naturalOrder())
                .filter(filter).collect(Collectors.toList());
    }
}
