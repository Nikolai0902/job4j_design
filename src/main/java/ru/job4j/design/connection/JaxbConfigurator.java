package ru.job4j.design.connection;

import ru.job4j.design.storage.Employees;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class JaxbConfigurator {

    public Marshaller get() {
        Marshaller marshaller = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return marshaller;
    }
}
