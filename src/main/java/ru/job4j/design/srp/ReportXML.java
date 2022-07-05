package ru.job4j.design.srp;

import ru.job4j.design.connection.JaxbConfigurator;
import ru.job4j.design.storage.Employee;
import ru.job4j.design.storage.Employees;
import ru.job4j.design.storage.Store;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Predicate;

public class ReportXML implements Report {

    public static final SimpleDateFormat DATE_FORMAT_XML = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    private Store store;
    private Marshaller marshaller;

    public ReportXML(Store store) {
        this.store = store;
        this.marshaller = new JaxbConfigurator().get();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String xml = "";
        List<Employee> employees = store.findBy(filter);
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Employees(employees), writer);
            xml = writer.getBuffer().toString();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return xml;
    }
}
