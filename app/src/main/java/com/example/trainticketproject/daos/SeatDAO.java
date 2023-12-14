package com.example.trainticketproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.trainticketproject.models.Seat;

import java.util.List;

@Dao
public interface SeatDAO {
    @Query("SELECT COUNT(*) FROM Seat WHERE trainId = :trainId")
    LiveData<Integer> getCountSeatsForTrain(Long trainId);

    @Query("SELECT * FROM Seat WHERE trainId = :trainId")
    LiveData<List<Seat>> getAllSeatsOfTrain(Long trainId);

    @Query("SELECT * FROM Seat WHERE seatCode = :seatCode and trainId = :trainId")
    LiveData<Seat> getSeatByCodeInTrain(Integer seatCode, Long trainId);

    @Query("SELECT * FROM Seat WHERE id = :id")
    LiveData<Seat> getSeatById(Long id);

    @Update
    void update(Seat seat);

    @Insert
    void insertAllSeats(List<Seat> seats);
}
