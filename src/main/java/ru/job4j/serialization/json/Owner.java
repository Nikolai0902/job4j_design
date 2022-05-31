package ru.job4j.serialization.json;

public class Owner {
    private String name;
    private String surname;
    private String dateBr;

    public Owner(String name, String surname, String dateBr) {
        this.name = name;
        this.surname = surname;
        this.dateBr = dateBr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateBr() {
        return dateBr;
    }

    public void setDateBr(String dateBr) {
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
