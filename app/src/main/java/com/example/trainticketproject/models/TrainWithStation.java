package com.example.trainticketproject.models;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

@Entity
public class TrainWithStation {
    @Embedded
    public Train train;

    @Relation(parentColumn = "departureStationID", entityColumn = "stationId")
    public Station departureStation;

    @Relation(parentColumn = "arrivalStationID", entityColumn = "stationId")
    public Station arrivalStation;
}
