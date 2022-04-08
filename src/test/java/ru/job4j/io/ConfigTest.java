package ru.job4j.io;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        Config config = new Config("./data/pair_without_comment.properties");
        config.load();
        assertEquals("postgres", config.value("hibernate.connection.username"));
        assertEquals("jdbc:postgresql://127.0.0.1:5432/trackstudio",
                config.value("hibernate.connection.url"));
        assertNull(config.value("name"));
    }

    @Test
    public void whenFileWithCommentsAndEmptyStrings() {
        Config config = new Config("./data/pairs_with_comments_and_empty_strings.properties");
        config.load();
        assertNull(config.value("# Pos"));
        assertNull(config.value(""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidPatternKeyAndValue() {
        Config config = new Config("./data/pairs_with_wrong_pattern_key_and_value.properties");
        config.load();
    }
}