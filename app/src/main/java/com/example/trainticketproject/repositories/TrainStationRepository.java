package com.example.trainticketproject.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.trainticketproject.daos.TrainStationDAO;
import com.example.trainticketproject.databases.TrainStaionRoomDatabase;
import com.example.trainticketproject.models.Train;

import java.util.List;

public class TrainStationRepository {
    private TrainStationDAO trainStationDao;
    private LiveData<List<Train>> trainsByStations;

    public TrainStationRepository(Application application) {
        TrainStaionRoomDatabase database = TrainStaionRoomDatabase.getInstance(application);
        trainStationDao = database.trainStationDao();
    }



}
