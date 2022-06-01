package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "owen")
public class Owner {

    @XmlAttribute
    private String name;
    @XmlAttribute
    private String surname;
    @XmlAttribute
    private String dateBr;

    public Owner() {
    }

    public Owner(String name, String surname, String dateBr) {
        this.name = name;
        this.surname = surname;
        this.dateBr = dateBr;
    }

    @Override
    public String toString() {
        return "Owner{" + "name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", dateBr='" + dateBr + '\''
                + '}';
    }
}
