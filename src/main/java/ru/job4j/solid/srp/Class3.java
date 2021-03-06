package ru.job4j.solid.srp;

import ru.job4j.collection.list.List;
import ru.job4j.question.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Class3 {

    public String printFormat(List<User> list) {
        StringBuilder text = new StringBuilder();
        text.append("Стобец1; Стобец1;");
        for (User i : list) {
            text.append(i.getId())
                    .append(i.getName())
                    .append(new SimpleDateFormat("dd MMM yyy GG").format(new Date().getTime()));
        }
        return text.toString();
    }
}
