package com.example.trainticketproject.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.trainticketproject.daos.VoucherDAO;
import com.example.trainticketproject.models.Voucher;

@Database(entities = { Voucher.class }, version = 1)
public abstract class VoucherRoomDatabase extends RoomDatabase {
    public abstract VoucherDAO voucherDAO();
    private static VoucherRoomDatabase INSTANCE;

    public static VoucherRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (VoucherRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            VoucherRoomDatabase.class,
                            "voucher_database"
                    ).build();
                }
            }
        }

        return INSTANCE;
    }
}
