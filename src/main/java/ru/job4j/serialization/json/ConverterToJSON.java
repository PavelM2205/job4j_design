package ru.job4j.serialization.json;

import org.json.JSONObject;

import java.util.List;

public class ConverterToJSON {
    public static void main(String[] args) {
        JSONObject jsonOwner = new JSONObject();
        jsonOwner.put("name", "Pavel");
        jsonOwner.put("address", "Krasnoyarsk");
        jsonOwner.put("passport", "1234-0987");
        List<String> list = List.of("05.04.2005", "07.09.2012", "15.03.2018");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("operable", true);
        jsonObject.put("model", "Toyota RAV-4");
        jsonObject.put("engineVolume", 3.5);
        jsonObject.put("owner", jsonOwner);
        jsonObject.put("repairDates", list);
        System.out.println(jsonObject);
        Car car = new Car(true, "Toyota RAV-4", 3.5,
                new Owner("Pavel", "Krasnoyarsk", "1234-0987"),
                new String[] {"05.04.2005", "07.09.2012", "15.03.2018"});
        System.out.println(new JSONObject(car));

    }
}
