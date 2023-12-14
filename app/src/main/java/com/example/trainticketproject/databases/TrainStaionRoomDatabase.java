package com.example.trainticketproject.databases;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.trainticketproject.daos.StationDAO;
import com.example.trainticketproject.daos.TrainDAO;
import com.example.trainticketproject.daos.TrainStationDAO;
import com.example.trainticketproject.models.Station;
import com.example.trainticketproject.models.Train;
import com.example.trainticketproject.utils.DateTimeConverter;

@Database(entities = {Station.class, Train.class}, version = 1)
@TypeConverters({ DateTimeConverter.class })
public abstract class TrainStaionRoomDatabase extends RoomDatabase {
    private static TrainStaionRoomDatabase instance;

    public abstract StationDAO stationDao();
    public abstract TrainDAO trainDao();
    public abstract TrainStationDAO trainStationDao();

    public static synchronized TrainStaionRoomDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            TrainStaionRoomDatabase.class, "trainstation_database")
                    .fallbackToDestructiveMigration()
                    .build();
            Log.d("TrainStaionRoomDatabase", "Database created");
        }
        return instance;
    }
}
