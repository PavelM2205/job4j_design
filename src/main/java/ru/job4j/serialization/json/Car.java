package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {
    private final boolean operable;
    private String model;
    private double engineVolume;
    private Owner owner;
    private String[] repairDates;

    public Car(boolean operable, String model, double engineVolume, Owner owner, String[] repairDates) {
        this.operable = operable;
        this.model = model;
        this.engineVolume = engineVolume;
        this.owner = owner;
        this.repairDates = repairDates;
    }

    @Override
    public String toString() {
        return "Car{" + "operable=" + operable
                + ", model='" + model + '\''
                + ", engineVolume=" + engineVolume
                + ", owner=" + owner
                + ", repairDate=" + Arrays.toString(repairDates) + '}';
    }
}
