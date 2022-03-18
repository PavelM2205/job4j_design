package ru.job4j.generics;

import java.util.Objects;

public class Role extends Base {

    private final String roleName;

    public Role(String id, String roleName) {
        super(id);
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return this.getId().equals(role.getId())
                && this.roleName.equals(role.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.roleName);
    }
}
