package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config conf = new Config("./app.properties");
        conf.load();
        Class.forName(conf.value("driver_class"));
        try (Connection connection = DriverManager.getConnection(
                conf.value("url"), conf.value("username"), conf.value("password"))
        ) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
