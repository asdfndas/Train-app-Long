package com.example.trainticketproject.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.trainticketproject.utils.LocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;

@Entity(foreignKeys = {
                @ForeignKey(entity = User.class,
                        parentColumns = "uid",
                        childColumns = "uid",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Seat.class,
                        parentColumns = "id",
                        childColumns = "seatNumber",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Train.class,
                        parentColumns = "trainId",
                        childColumns = "trainId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Train.class,
                        parentColumns = "trainId",
                        childColumns = "trainId",
                        onDelete = ForeignKey.CASCADE)
        })
public class Ticket {
    @PrimaryKey(autoGenerate = true)
    private Long ticketId;
    private Long uid;
    private Long trainId;
    private Long seatNumber;
    private LocalDateTime issuedDate;
    private Status status;

    public Ticket(Long uid, Long trainId, Long seatNumber, LocalDateTime issuedDate, Status status) {
        this.uid = uid;
        this.trainId = trainId;
        this.seatNumber = seatNumber;
        this.issuedDate = issuedDate;
        this.status = status;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Long seatNumber) {
        this.seatNumber = seatNumber;
    }

    public LocalDateTime getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDateTime issuedDate) {
        this.issuedDate = issuedDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public String toJson() {
        Gson gson = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .setPrettyPrinting()
                    .create();
        }
        return gson.toJson(this);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", uid=" + uid +
                ", trainId=" + trainId +
                ", seatNumber=" + seatNumber +
                ", issuedDate=" + issuedDate +
                ", status=" + status +
                "}";
    }
}
