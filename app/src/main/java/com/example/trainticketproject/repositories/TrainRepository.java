package com.example.trainticketproject.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.trainticketproject.daos.TrainDAO;
import com.example.trainticketproject.databases.TrainRoomDatabase;
import com.example.trainticketproject.models.Train;

import java.util.List;

public class TrainRepository {
    private final TrainDAO trainDAO;

    public TrainRepository(Application application) {
        TrainRoomDatabase trainDatabase = TrainRoomDatabase.getDatabase(application);
        trainDAO = trainDatabase.trainDAO();
    }

    public LiveData<Train> getTrainById(Long trainId) {
        return trainDAO.getTrainById(trainId);
    }

    public void insertTrain(Train train) {
        trainDAO.insertTrain(train);
    }

    public void insertMultipleTrains(List<Train> trains) {
        trainDAO.insertMultipleTrains(trains);
    }

    public LiveData<List<Train>> getTrainByDepartureStationID(Long id) {
        return trainDAO.getTrainsByDepartureStationID(id);
    }
    public LiveData<List<Train>> getTrainsByDepartureStationIDs(List<Long> stationIds) {
        return trainDAO.getTrainsByDepartureStationIDs(stationIds);
    }

    public LiveData<List<Train>> getTrainsByArrivalStationIDs(List<Long> stationIds) {
        return trainDAO.getTrainsByArrivalStationIDs(stationIds);
    }

    public LiveData<List<Train>> getAllTrains() {
        return trainDAO.getAllTrains();
    }

}
