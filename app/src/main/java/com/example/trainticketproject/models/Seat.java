package com.example.trainticketproject.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Seat {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private Integer seatCode;
    @ColumnInfo(defaultValue = "AVAILABLE")
    private Status status;
    private Long trainId;
    private Long ticketId;
    private Long uid;

    public Seat(Integer seatCode, Status status, Long trainId, Long uid, Long ticketId) {
        if (seatCode >= 1 && seatCode <= 40) {
            this.seatCode = seatCode;
        }
        this.status = status;
        this.trainId = trainId;
        this.uid = uid;
        this.ticketId = ticketId;
    }

    public int getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(int seatCode) {
        this.seatCode = seatCode;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }
}
