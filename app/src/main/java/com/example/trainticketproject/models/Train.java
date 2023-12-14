package com.example.trainticketproject.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity
public class Train {
    @PrimaryKey(autoGenerate = true)
    private Long trainId;
    private String trip;
    private Long departureStationID;
    private Long arrivalStationID;

    public Long getDepartureStationID() {
        return departureStationID;
    }

    public void setDepartureStationID(Long departureStationID) {
        this.departureStationID = departureStationID;
    }

    public Long getArrivalStationID() {
        return arrivalStationID;
    }

    public void setArrivalStationID(Long arrivalStationID) {
        this.arrivalStationID = arrivalStationID;
    }

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    private int price;
    private int totalSeats;

    public Train(String trip, Long departureStationID, Long arrivalStationID, LocalDateTime departureTime, LocalDateTime arrivalTime, int price, int totalSeats) {
        this.trip = trip;
        this.departureStationID = departureStationID;
        this.arrivalStationID = arrivalStationID;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.totalSeats = totalSeats;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    @Override
    public String toString() {
        return "Train{" +
                "trainId=" + trainId +
                ", trip='" + trip + '\'' +
                ", departureStation='" + departureStationID + '\'' +
                ", arrivalStation='" + arrivalStationID + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", price=" + price +
                ", totalSeats=" + totalSeats +
                '}';
    }
}
