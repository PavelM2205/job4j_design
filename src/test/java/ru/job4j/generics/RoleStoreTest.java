package ru.job4j.generics;

import org.junit.Test;

import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRolenameIsPavel() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pavel"));
        assertEquals("Pavel", store.findById("1").getRoleName());
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pavel"));
        assertNull(store.findById("2"));
    }

    @Test
    public void whenAddDuplicateAndAndFindRolenameIsPavel() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pavel"));
        store.add(new Role("1", "Petr"));
        assertEquals("Pavel", store.findById("1").getRoleName());
    }

    @Test
    public void whenReplaceThenRolenameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pavel"));
        store.replace("1", new Role("1", "Petr"));
        assertEquals("Petr", store.findById("1").getRoleName());
    }

    @Test
    public void whenReplaceRoleThenNoChangeRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pavel"));
        store.replace("2", new Role("2", "Petr"));
        assertNull(store.findById("2"));
        assertEquals("Pavel", store.findById("1").getRoleName());
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pavel"));
        store.delete("1");
        assertNull(store.findById("1"));
    }

    @Test
    public void whenNoDeleteRoleThenRolenameIsPavel() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pavel"));
        store.delete("2");
        assertEquals("Pavel", store.findById("1").getRoleName());
    }
}