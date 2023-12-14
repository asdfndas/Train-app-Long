package com.example.trainticketproject.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.trainticketproject.daos.SeatDAO;
import com.example.trainticketproject.databases.SeatRoomDatabase;
import com.example.trainticketproject.models.Seat;

import java.util.List;

public class SeatRepository {
    private SeatDAO seatDAO;

    public SeatRepository(Application application) {
        SeatRoomDatabase seatRoomDatabase = SeatRoomDatabase.getDatabase(application);
        seatDAO = seatRoomDatabase.seatDAO();
    }

    public LiveData<List<Seat>> getAllSeatsOfTrain(Long trainId) {
        return seatDAO.getAllSeatsOfTrain(trainId);
    }

    public void insertAllSeats(List<Seat> seats) {
        seatDAO.insertAllSeats(seats);
    }

    public LiveData<Seat> getSeatByCodeInTrain(Integer seatCode, Long trainId) {
        return seatDAO.getSeatByCodeInTrain(seatCode, trainId);
    }

    public void update(Seat seat) {
        seatDAO.update(seat);
    }

    public LiveData<Integer> getCountSeatsForTrain(Long trainId) {
        return seatDAO.getCountSeatsForTrain(trainId);
    }

    public LiveData<Seat> getSeatById(Long seatId) {
        return seatDAO.getSeatById(seatId);
    }
}
