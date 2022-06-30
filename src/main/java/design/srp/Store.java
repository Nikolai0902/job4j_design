package design.srp;

import java.util.List;
import java.util.function.Predicate;

/**
 * Интерфейс для осуществления доступа к БД.
 */
public interface Store {

    public void add(Employee em);
    List<Employee> findBy(Predicate<Employee> filter);
}
