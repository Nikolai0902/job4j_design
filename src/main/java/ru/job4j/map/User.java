package ru.job4j.map;

import java.text.SimpleDateFormat;
import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");

    public User() {
    }

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday.getTime() + '}';
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
        return children == user.children && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday)
                && Objects.equals(sdf, user.sdf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday, sdf);
    }

    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar(2021, 03, 17);
        User nik = new User("Nikolai", 2,   calendar);
        User nikTwo = new User("Nikolai", 2,  calendar);

        System.out.println(nik.hashCode());
        System.out.println(nikTwo.hashCode());
        System.out.println(nik.equals(nikTwo));

        Map<User, Object> map = new HashMap<>();

        map.put(nik, new Object());
        map.put(nikTwo, new Object());

        System.out.println(map);
        System.out.println(map);
    }
}
