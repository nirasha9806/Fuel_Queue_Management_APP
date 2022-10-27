package com.example.eadassignment;

public class Queue {
    String username, arrivalTime, departTime, stationId, vehicleType;

    public Queue(String username, String arrivalTime, String departTime, String stationId, String vehicleType) {
        this.username = username;
        this.arrivalTime = arrivalTime;
        this.departTime = departTime;
        this.stationId = stationId;
        this.vehicleType = vehicleType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}
