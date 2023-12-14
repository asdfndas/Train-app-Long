package com.example.trainticketproject.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.trainticketproject.daos.StationDAO;
import com.example.trainticketproject.models.Station;

@Database(entities = {Station.class}, version = 1)
public abstract class StationRoomDatabase extends RoomDatabase {
    public abstract StationDAO stationDAO();

    private static StationRoomDatabase INSTANCE;
    public static synchronized StationRoomDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            synchronized (StationRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    StationRoomDatabase.class, "station_database").build();
                }}}
        return INSTANCE;
    }

}
