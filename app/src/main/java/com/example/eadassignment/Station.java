package com.example.eadassignment;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.List;

public class Station {
    private String name, city;
    private List<JSONObject> fuelDetails;

    public List<JSONObject> getFuelDetails() {
        return fuelDetails;
    }

    public void setFuelDetails(List<JSONObject> fuelDetails) {
        this.fuelDetails = fuelDetails;
    }

    public Station(String name, String city, List<JSONObject> fuelDetails) {
        this.name = name;
        this.city = city;
        this.fuelDetails = fuelDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}
