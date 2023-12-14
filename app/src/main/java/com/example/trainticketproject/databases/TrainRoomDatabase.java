package com.example.trainticketproject.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.trainticketproject.daos.TrainDAO;
import com.example.trainticketproject.models.Train;
import com.example.trainticketproject.utils.DateTimeConverter;

@Database(entities = { Train.class }, version = 1)
@TypeConverters({ DateTimeConverter.class })
public abstract class TrainRoomDatabase extends RoomDatabase {
    public abstract TrainDAO trainDAO();
    private static TrainRoomDatabase INSTANCE;

    public static TrainRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TrainRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            TrainRoomDatabase.class,
                            "train_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
