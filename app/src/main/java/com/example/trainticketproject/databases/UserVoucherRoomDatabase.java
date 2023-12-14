package com.example.trainticketproject.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.trainticketproject.daos.UserDAO;
import com.example.trainticketproject.daos.UserVoucherCrossRefDAO;
import com.example.trainticketproject.daos.UserVoucherDAO;
import com.example.trainticketproject.daos.VoucherDAO;
import com.example.trainticketproject.models.User;
import com.example.trainticketproject.models.UserVoucherCrossRef;
import com.example.trainticketproject.models.Voucher;

@Database(entities = {User.class, Voucher.class,UserVoucherCrossRef.class}, version = 1)
public abstract class UserVoucherRoomDatabase extends RoomDatabase {
    public abstract UserVoucherDAO userVoucherDAO();
    public abstract UserVoucherCrossRefDAO userVoucherCrossRefDAO();
    private static UserVoucherRoomDatabase INSTANCE;

    public static UserVoucherRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserVoucherRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            UserVoucherRoomDatabase.class,
                            "user_voucher_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
