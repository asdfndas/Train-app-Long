package com.example.trainticketproject.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.trainticketproject.models.Station;
import com.example.trainticketproject.repositories.StationRepository;

import java.util.List;

public class StationViewModel extends AndroidViewModel {

    private StationRepository stationRepository;
    private LiveData<List<Station>> allStations;

    public StationViewModel(Application application) {
        super(application);
        stationRepository = new StationRepository(application);
        allStations = stationRepository.getAllStations();
    }
    public LiveData<Station> getStationById(Long stationId) {
        return stationRepository.getStationById(stationId);
    }
    public LiveData<List<Station>> getAllStations() {
        return allStations;
    }

    public void insert(Station station) {
        stationRepository.insert(station);
    }

    public void insertSampleStations(List<Station> stations) {
        stationRepository.insertSampleStations(stations);
    }

    public LiveData<String> getStationNameByID(Long stationId) {
        return stationRepository.getStationNameById(stationId);
    }

    public LiveData<List<Long>> searchStationIDsByName(String name) {
        return stationRepository.searchStationIDsByName(name);
    }
}

