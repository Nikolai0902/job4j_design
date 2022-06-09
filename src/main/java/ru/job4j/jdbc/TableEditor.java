package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;

    private static Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        connection = null;
    }

    private static Connection getConnection() throws Exception {
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        return DriverManager.getConnection(
                properties.getProperty("hibernate.connection.url"),
                properties.getProperty("hibernate.connection.username"),
                properties.getProperty("hibernate.connection.password")
        );
    }

    public static void main(String[] args) throws Exception {
        Properties r = new Properties();
        r.load(new FileReader("app.properties"));
        TableEditor tableEditor = new TableEditor(r);
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                tableEditor.createTable("tab", statement);
                System.out.println(getTableScheme(connection, "tab"));
                tableEditor.addColumn("tab", "age", "int", statement);
                System.out.println(getTableScheme(connection, "tab"));
                tableEditor.renameColumn("tab", "age", "Vin", statement);
                System.out.println(getTableScheme(connection, "tab"));
                tableEditor.dropColumn("tab", "Vin", statement);
                System.out.println(getTableScheme(connection, "tab"));
                tableEditor.dropTable("tab", statement);
                System.out.println(getTableScheme(connection, "tab"));
            }
        }
    }

    public void createTable(String tableName, Statement statement) throws Exception {
                String sql = String.format(
                        "create table " + tableName + "();"
                );
                statement.execute(sql);
    }

    public void dropTable(String tableName, Statement statement) throws Exception {
                String sql = String.format(
                        "DROP TABLE " + tableName + ";"
                );
                statement.execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type, Statement statement) throws Exception {
                String sql = String.format(
                        "ALTER TABLE " + tableName
                                + " ADD " + columnName + " " + type + ";"
                );
                statement.execute(sql);
    }

    public void dropColumn(String tableName, String columnName, Statement statement) throws Exception {
                String sql = String.format(
                        "ALTER TABLE " + tableName
                                + " DROP COLUMN " + columnName + ";"
                );
                statement.execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName, Statement statement) throws Exception {
                String sql = String.format(
                        "ALTER TABLE " + tableName
                                + " RENAME COLUMN " + columnName + " TO " + newColumnName + ";"
                );
                statement.execute(sql);
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
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
