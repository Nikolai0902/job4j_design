package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int a = 0;
        int c = 0;
        int d = 0;
        LinkedHashMap<Integer, User> mapCurrent = new LinkedHashMap<>();
        current.forEach(s -> mapCurrent.put(s.getId(), s));
        for (User user : previous) {
            User userCurr = mapCurrent.get(user.getId());
            if (userCurr == null) {
                d++;
            } else if (!user.getName().equals(userCurr.getName())) {
                c++;
            }
        }
        a += mapCurrent.size() - previous.size() + d;
        return new Info(a, c, d);
    }
}
