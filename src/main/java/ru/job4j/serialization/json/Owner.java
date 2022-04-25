package ru.job4j.serialization.json;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Owner {
    @XmlAttribute
    String name;
    @XmlAttribute
    String address;
    @XmlAttribute
    String passport;

    public Owner() {
    }

    public Owner(String name, String address, String passport) {
        this.name = name;
        this.address = address;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPassport() {
        return passport;
    }

    @Override
    public String toString() {
        return "Owner{" + "name='" + name + '\''
                + ", address='" + address + '\''
                + ", passport='" + passport + '\'' + '}';
    }
}
