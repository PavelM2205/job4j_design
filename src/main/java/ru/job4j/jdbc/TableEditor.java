package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private final Properties properties;
    private Statement statement;

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver_class"));
        connection = DriverManager.getConnection(
                properties.getProperty("url"), properties.getProperty("username"),
                properties.getProperty("password"));
        statement = connection.createStatement();
    }

    public void createTable(String tableName) throws SQLException {
        statement.execute(String.format("create table %s();", tableName));
    }

    public void dropTable(String tableName) throws SQLException {
        statement.execute(String.format("drop table %s;", tableName));
    }

    public void addColumn(String tableName, String columnName) throws SQLException {
        statement.execute(String.format("alter table %s add column %s varchar(255);",
                tableName, columnName));
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        statement.execute(String.format("alter table %s drop column %s;",
                tableName, columnName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName)
            throws SQLException {
        statement.execute(String.format("alter table %s rename column %s to %s",
                tableName, columnName, newColumnName));
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
                        metaData.getColumnName(i), metaData.getColumnTypeName(i)));
            }
            return buffer.toString();
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
        if (statement != null) {
            statement.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (BufferedReader reader = new BufferedReader(
                new FileReader("./app.properties"))) {
            properties.load(reader);
        }
        try (TableEditor editor = new TableEditor(properties)) {
            String tableName = "test_table";
            editor.createTable(tableName);
            System.out.println(TableEditor.getTableScheme(editor.connection, tableName));
            System.out.println();
            editor.addColumn(tableName, "test_column");
            System.out.println(TableEditor.getTableScheme(editor.connection, tableName));
            System.out.println();
            editor.renameColumn(tableName, "test_column",
                    "new_col_name");
            System.out.println(TableEditor.getTableScheme(editor.connection, tableName));
            System.out.println();
            editor.dropColumn(tableName, "new_col_name");
            System.out.println(TableEditor.getTableScheme(editor.connection, tableName));
            System.out.println();
            editor.dropTable(tableName);
        }
    }
}
