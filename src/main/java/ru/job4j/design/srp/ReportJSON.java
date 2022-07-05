package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.design.connection.JsonConfigurator;
import ru.job4j.design.storage.Employee;
import ru.job4j.design.storage.Store;

import java.util.List;
import java.util.function.Predicate;

public class ReportJSON implements Report {

    private Store store;
    private Gson gson;

    public ReportJSON(Store store) {
        this.store = store;
        this.gson = new JsonConfigurator().get();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        return gson.toJson(employees);
    }
}
