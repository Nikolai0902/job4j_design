package ru.job4j.solid;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;

/**
 * Модель данных с лишней логикой.
 * метод userSerialization нарушает SRP.
 */
public class User implements Serializable {
    private String name;
    private String surname;
    private int id;

    public User() {
    }

    public User(String name, String surname, int id) {
        this.name = name;
        this.surname = surname;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(surname, user.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, id);
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", surname='"
                + surname + '\'' + ", id=" + id + '}';
    }

    public static void userSerialization(User user) throws IOException {
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos =
                     new ObjectOutputStream(fos)) {
            oos.writeObject(user);
        }
    }
}
