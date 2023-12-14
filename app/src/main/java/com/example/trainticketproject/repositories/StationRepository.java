package com.example.trainticketproject.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.trainticketproject.daos.StationDAO;
import com.example.trainticketproject.databases.StationRoomDatabase;
import com.example.trainticketproject.models.Station;

import java.util.List;

public class StationRepository {

    private StationDAO stationDao;
    private LiveData<List<Station>> allStations;

    public StationRepository(Application application) {
        StationRoomDatabase database = StationRoomDatabase.getINSTANCE(application);
        stationDao = database.stationDAO();
        allStations = stationDao.getAllStations();
    }

    public LiveData<List<Station>> getAllStations() {
        return allStations;
    }

    public void insert(Station station) {   stationDao.insetStation(station);
    }

    public LiveData<Station> getStationById(Long stationId) {
        return stationDao.getStationById(stationId);
    }
    public LiveData<String> getStationNameById(Long stationId) {
        return stationDao.getStationNameByID(stationId);
    }
    public void insertSampleStations(List<Station> stations) {
        stationDao.insertMultipleStations(stations);
    }

    public LiveData<List<Long>> searchStationIDsByName(String name) {
        return stationDao.searchStationsIdByName(name);
    }
}

