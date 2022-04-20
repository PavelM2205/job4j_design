package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Converter {
    public static void main(String[] args) {
        final Car car = new Car(true, "Toyota RAV-4", 3.5,
                new Owner("Pavel", "Krasnoyarsk", "1234-0987"),
                new String[] {"05.04.2005", "07.09.2012", "15.03.2018"});

        Gson gson = new GsonBuilder().create();
        String carJson = gson.toJson(car);
        System.out.println(carJson);
        final Car carFromJson = gson.fromJson(carJson, Car.class);
        System.out.println(carFromJson);
    }
}
