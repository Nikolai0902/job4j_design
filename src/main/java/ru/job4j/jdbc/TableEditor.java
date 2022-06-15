package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;

    private static Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    /**
     * Метод инициализации соединения с базой данных.
     */
    private void initConnection() {
        try {
            connection = getConnection();
        } catch (Exception e) {
            System.out.println("Неверный логин или пароль");
        }
    }

    /**
     * Метод регистрирует драйвер в системе и получает соединение с базой данных,
     * используя данные из проперти.
     *
     * @return Объект соединения с базой данных.
     * @throws Exception Исключения соединения с базой данных.
     */
    private static Connection getConnection() throws Exception {
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        return DriverManager.getConnection(
                properties.getProperty("hibernate.connection.url"),
                properties.getProperty("hibernate.connection.username"),
                properties.getProperty("hibernate.connection.password")
        );
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        try (TableEditor tableEditor = new TableEditor(config)) {
            tableEditor.createTable("tab");
            System.out.println(tableEditor.getTableScheme("tab"));
            tableEditor.addColumn("tab", "age", "int");
            System.out.println(tableEditor.getTableScheme("tab"));
            tableEditor.renameColumn("tab", "age", "Vin");
            System.out.println(tableEditor.getTableScheme("tab"));
            tableEditor.dropColumn("tab", "Vin");
            System.out.println(tableEditor.getTableScheme("tab"));
            tableEditor.dropTable("tab");
            System.out.println(tableEditor.getTableScheme("tab"));
        }
    }


    public void createTable(String tableName) throws Exception {
        String sql = String.format(
                "create table " + tableName + "();"
        );
        executeQuery(sql);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format(
                "DROP TABLE " + tableName + ";"
        );
        executeQuery(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format(
                "ALTER TABLE " + tableName
                        + " ADD " + columnName + " " + type + ";"
        );
        executeQuery(sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format(
                "ALTER TABLE " + tableName
                        + " DROP COLUMN " + columnName + ";"
        );
        executeQuery(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format(
                "ALTER TABLE " + tableName
                        + " RENAME COLUMN " + columnName + " TO " + newColumnName + ";"
        );
        executeQuery(sql);
    }

    private void executeQuery(String query) {
        try (Statement stat = connection.createStatement()) {
            stat.execute(query);
        } catch (SQLException sq) {
            sq.printStackTrace();
        }
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
