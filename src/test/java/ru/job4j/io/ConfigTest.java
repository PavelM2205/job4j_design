package ru.job4j.io;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutCommentAndEmptyString() {
        Config config = new Config("./data/pairs_without_comment_and_empty_strings.properties");
        config.load();
        assertEquals("Pavel://127", config.value("username"));
        assertEquals("MyPassword", config.value("password"));
        assertNull(config.value("surname"));
    }

    @Test
    public void whenFileWithComment() {
        Config config = new Config("./data/pair_with_comment.properties");
        config.load();
        assertNull(config.value("# Comment"));
        assertEquals("MyPassword", config.value("password"));
        assertEquals(1, config.getSize());
    }

    @Test
    public void whenFileWithEmptyString() {
        Config config = new Config("./data/pairs_with_empty_string.properties");
        config.load();
        assertEquals(2, config.getSize());
        assertEquals("MyPassword", config.value("password"));
        assertEquals("Pavel://127", config.value("username"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoEqualSignThenMustBeException() {
        Config config = new Config("./data/pair_with_wrong_pattern_no_equal_sign.properties");
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoKeyThenMustBeException() {
        Config config = new Config("./data/pair_with_wrong_pattern_no_key.properties");
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoValueThenMustBeException() {
        Config config = new Config("./data/pair_with_wrong_pattern_no_value.properties");
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenOnlyEqualSignThenMustBeException() {
        Config config = new Config("./data/pair_with_wrong_pattern_no_value.properties");
        config.load();
    }
}