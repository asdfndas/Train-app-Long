package com.example.trainticketproject.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class TrainWithTickets {
    @Embedded
    public Train train;

    @Relation(parentColumn = "trainId", entityColumn = "trainId")
    public List<Ticket> tickets;
}
