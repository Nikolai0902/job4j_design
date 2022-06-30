package ru.job4j.solid;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Нарушение принципа SRP.
 * Соединение происходит с определенным драйвером и localHost
 * которое может поменяться.
 * Для загрузки настроек нужно использовать Properties.
 */
public class Class3 {

    public Connection buildConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/someDb?characterEncoding=UTF-8&characterSetResults=UTF-8&serverTimezone=UTC", "user01", "12345qwert");
        return connection;
    }
}
