package com.example.eadassignment;

public class FuelDetails {
    String arrivalTime, finishTime,fuelType;

    public FuelDetails(String arrivalTime, String finishTime, String fuelType) {
        this.arrivalTime = arrivalTime;
        this.finishTime = finishTime;
        this.fuelType = fuelType;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
