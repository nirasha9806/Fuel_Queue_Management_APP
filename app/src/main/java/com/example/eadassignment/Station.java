package com.example.eadassignment;

import java.lang.reflect.Array;

public class Station {
    private String name, city;
//    private Array fuelDetails;

    public Station(String name, String city, Array fuelDetails) {
        this.name = name;
        this.city = city;
//        this.fuelDetails = fuelDetails;
    }

    public String getName() {
        return name;
    }

//    public Array getFuelDetails() {
//        return fuelDetails;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

//    public void setFuelDetails(Array fuelDetails) {
//        this.fuelDetails = fuelDetails;
//    }

    public String getCity() {
        return city;
    }
}
