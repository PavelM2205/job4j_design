package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    private void check(String str) {
        if (!str.matches(
                "^[^\\s;][a-zA-Z0-9\\s\\._-]{1,};{1}[a-zA-Z0-9\\s\\._-]{1,}@[a-zA-Z0-9\\s\\._-]{1,};$")) {
            throw new IllegalArgumentException("String format must be: NAME;MAIL;");
        }
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            reader.lines()
                    .peek(this::check)
                    .map(str -> str.split(";", 3))
                    .map(mas -> new User(mas[0], mas[1]))
                    .forEach(users::add);
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (var user : users) {
                try (PreparedStatement statement = connection.prepareStatement(
                        "insert into users(name, email) values(?, ?);")) {
                    statement.setString(1, user.name);
                    statement.setString(2, user.email);
                    statement.execute();
                }
            }

        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream(
                "app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }
}
