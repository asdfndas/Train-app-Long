package com.example.trainticketproject.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.trainticketproject.daos.UserDAO;
import com.example.trainticketproject.models.User;

@Database(entities = { User.class }, version = 1)
public abstract class UserRoomDatabase extends RoomDatabase {
    public abstract UserDAO userDAO();
    private static UserRoomDatabase INSTANCE;

    public static UserRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            UserRoomDatabase.class,
                            "user_database"
                    ).build();
                }
            }
        }

        return INSTANCE;
    }
}
