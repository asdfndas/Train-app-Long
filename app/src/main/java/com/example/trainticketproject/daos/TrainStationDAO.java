package com.example.trainticketproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Relation;

import com.example.trainticketproject.models.Station;
import com.example.trainticketproject.models.Train;

import java.util.List;

@Dao
public interface TrainStationDAO {
    @Query("SELECT * FROM Train " +
            "INNER JOIN Station AS departure ON Train.departureStationID = departure.stationId " +
            "INNER JOIN Station AS arrival ON Train.arrivalStationID = arrival.stationId " +
            "WHERE departure.stationName = :departureStationName AND arrival.stationName = :arrivalStationName")
    LiveData<List<Train>> getTrainsByStations(String departureStationName, String arrivalStationName);

}


