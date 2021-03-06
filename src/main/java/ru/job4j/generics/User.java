package ru.job4j.generics;

import java.util.Objects;

public class User extends Base {

    private final String userName;

    public User(String id, String name) {
        super(id);
        this.userName = name;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return this.getId().equals(user.getId())
                && this.userName.equals(user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
