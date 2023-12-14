package com.example.trainticketproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.trainticketproject.models.Station;

import java.util.List;

@Dao
public interface StationDAO {
    @Query("SELECT * FROM Station WHERE stationId = :id")
    public LiveData<Station> getStationById(Long id);

    @Query("SELECT * FROM Station")
    public LiveData<List<Station>> getAllStations();

    @Insert
    public void insertMultipleStations(List<Station> stationList);

    @Insert
    public void insetStation(Station station);

    @Query("SELECT stationName FROM Station WHERE stationId = :id")
    public LiveData<String> getStationNameByID(Long id);

    @Query("SELECT stationId FROM Station WHERE stationName LIKE '%' || :name || '%';\n")
    public LiveData<List<Long>> searchStationsIdByName(String name);
}
