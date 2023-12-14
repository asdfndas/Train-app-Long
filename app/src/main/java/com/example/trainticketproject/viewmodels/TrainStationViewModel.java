package com.example.trainticketproject.viewmodels;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.trainticketproject.models.Train;
import com.example.trainticketproject.repositories.TrainStationRepository;

import java.util.List;

public class TrainStationViewModel extends AndroidViewModel {
    private TrainStationRepository repository;

    public TrainStationViewModel(Application application) {
        super(application);
        repository = new TrainStationRepository(application);
    }

//    public LiveData<List<Train>> searchTrainsByDepartureStationName(String departureStationName) {
//        return repository.searchTrainsByDepartureStationName(departureStationName);
//    }
//
//    public LiveData<List<Train>> searchTrainsByArrivalStationName(String arrivalStationName) {
//        return repository.searchTrainsByArrivalStationName(arrivalStationName);
//    }
}