package com.example.trainticketproject.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.trainticketproject.models.Train;
import com.example.trainticketproject.repositories.TicketRepository;
import com.example.trainticketproject.repositories.TrainRepository;

import java.util.List;

public class TrainViewModel extends AndroidViewModel {
    private final TrainRepository trainRepository;
    private final TicketRepository ticketRepository;

    public TrainViewModel(@NonNull Application application) {
        super(application);
        trainRepository = new TrainRepository(application);
        ticketRepository = new TicketRepository(application);
    }

    public LiveData<Train> getTrainById(Long trainId) {
        return trainRepository.getTrainById(trainId);
    }

    public LiveData<Integer> getAvailableSeatsCount(Long trainId) {
        return ticketRepository.getAvailableTicketCountByTrainId(trainId);
    }

    public void insertTrain(Train train) {
        trainRepository.insertTrain(train);
    }

    public void insertMultipleTrains(List<Train> trains) {
        trainRepository.insertMultipleTrains(trains);
    }

    public LiveData<List<Train>> getAllTrains() {
        return trainRepository.getAllTrains();
    }

    public LiveData<List<Train>> getTrainsByArrivalStationIDs(List<Long> stationIds) {
        return trainRepository.getTrainsByArrivalStationIDs(stationIds);
    }
    public LiveData<List<Train>> getTrainsByDepartureStationIDs(List<Long> stationIds) {
        return trainRepository.getTrainsByDepartureStationIDs(stationIds);
    }

}
