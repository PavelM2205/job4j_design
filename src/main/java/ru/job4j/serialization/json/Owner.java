package ru.job4j.serialization.json;

public class Owner {
    String name;
    String address;
    String passport;

    public Owner(String name, String address, String passport) {
        this.name = name;
        this.address = address;
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "Owner{" + "name='" + name + '\''
                + ", address='" + address + '\''
                + ", passport='" + passport + '\'' + '}';
    }
}
