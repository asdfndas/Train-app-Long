package com.example.trainticketproject.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.trainticketproject.daos.SeatDAO;
import com.example.trainticketproject.models.Seat;

@Database(entities = { Seat.class }, version = 1)
public abstract class SeatRoomDatabase extends RoomDatabase {
    public abstract SeatDAO seatDAO();
    private static volatile SeatRoomDatabase INSTANCE;

    public static SeatRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SeatRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            SeatRoomDatabase.class,
                            "seat_database"
                    ).build();
                }
            }
        }

        return INSTANCE;
    }
}
