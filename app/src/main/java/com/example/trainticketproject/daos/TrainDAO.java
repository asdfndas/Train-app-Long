package com.example.trainticketproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.trainticketproject.models.Train;

import java.util.List;

@Dao
public interface TrainDAO {
    @Insert
    void insertTrain(Train train);

    @Update
    void updateTrain(Train train);

    @Delete
    void deleteTrain(Train train);

    @Query("SELECT * FROM Train WHERE trainId = :id")
    LiveData<Train> getTrainById(Long id);

    @Query("SELECT * FROM Train")
    LiveData<List<Train>> getAllTrains();

    @Query("SELECT * FROM Train WHERE departureStationID = :departureStationId AND arrivalStationID = :arrivalStationId")
    LiveData<List<Train>> getTrainsByStationsID(Long departureStationId, Long arrivalStationId);

    @Query("SELECT * FROM Train WHERE departureStationID = :departureStationId")
    LiveData<List<Train>> getTrainsByDepartureStationID(Long departureStationId);

    @Query("SELECT * FROM Train WHERE arrivalStationID = :arrivalStationId")
    LiveData<List<Train>> getTrainsByArrivalStationsID(Long arrivalStationId);

    @Insert
    void insertMultipleTrains(List<Train> trains);

    @Query("SELECT * FROM Train WHERE departureStationId IN (:stationIds)")
    LiveData<List<Train>> getTrainsByDepartureStationIDs(List<Long> stationIds);

    @Query("SELECT * FROM Train WHERE arrivalStationID IN (:stationIds)")
    LiveData<List<Train>> getTrainsByArrivalStationIDs(List<Long> stationIds);

}
