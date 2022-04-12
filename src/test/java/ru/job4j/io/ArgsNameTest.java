package ru.job4j.io;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArgsNameTest {

    @Test
    public void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        assertEquals("512", jvm.get("Xmx"));
    }

    @Test
    public void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx=512"});
        assertEquals("512", jvm.get("Xmx"));
    }

    @Test
    public void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[] {"-request=?msg=Exit="});
        assertEquals("?msg=Exit=", jvm.get("request"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512"});
        jvm.get("Xms");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoDashSymbolBefore() {
        ArgsName jvm = ArgsName.of(new String[] {"Xmx=512"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoValue() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx="});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoKey() {
        ArgsName jvm = ArgsName.of(new String[] {"=512"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoKeyAndDashIs() {
        ArgsName jvm = ArgsName.of(new String[] {"-=512"});
    }
}