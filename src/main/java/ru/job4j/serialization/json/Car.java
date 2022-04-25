package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private boolean operable;
    @XmlAttribute
    private String model;
    @XmlAttribute
    private double engineVolume;
    private Owner owner;
    @XmlElementWrapper(name = "repairedates")
    @XmlElement(name = "repairedate")
    private String[] repairDates;

    public Car() {
    }

    public Car(boolean operable, String model, double engineVolume, Owner owner, String[] repairDates) {
        this.operable = operable;
        this.model = model;
        this.engineVolume = engineVolume;
        this.owner = owner;
        this.repairDates = repairDates;
    }

    public boolean isOperable() {
        return operable;
    }

    public String getModel() {
        return model;
    }

    public double getEngineVolume() {
        return engineVolume;
    }

    public Owner getOwner() {
        return owner;
    }

    public String[] getRepairDates() {
        return repairDates;
    }

    @Override
    public String toString() {
        return "Car{" + "operable=" + operable
                + ", model='" + model + '\''
                + ", engineVolume=" + engineVolume
                + ", owner=" + owner
                + ", repairDates=" + Arrays.toString(repairDates) + '}';
    }
}
