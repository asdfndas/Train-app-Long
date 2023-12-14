package com.example.trainticketproject.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.trainticketproject.models.Seat;
import com.example.trainticketproject.repositories.SeatRepository;

import java.util.List;

public class SeatViewModel extends AndroidViewModel {
    private final SeatRepository seatRepository;
    public SeatViewModel(@NonNull Application application) {
        super(application);
        seatRepository = new SeatRepository(application);
    }

    public LiveData<List<Seat>> getAllSeatsOfTrain(Long trainId) {
        return seatRepository.getAllSeatsOfTrain(trainId);
    }

    public void insertAllSeats(List<Seat> seats) {
        seatRepository.insertAllSeats(seats);
    }

    public LiveData<Seat> getSeatByCodeInTrain(Integer seatCode, Long trainId) {
        return seatRepository.getSeatByCodeInTrain(seatCode, trainId);
    }

    public void update(Seat seat) {
        seatRepository.update(seat);
    }

    public LiveData<Integer> getCountSeatsForTrain(Long trainId) {
        return seatRepository.getCountSeatsForTrain(trainId);
    }

    public LiveData<Seat> getSeatById(Long seatId) {
        return seatRepository.getSeatById(seatId);
    }
}
