package com.example.trainticketproject.databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.trainticketproject.daos.PaymentDAO;
import com.example.trainticketproject.models.Payment;

@Database(entities = { Payment.class }, version = 1)
public abstract class PaymentRoomDatabase extends RoomDatabase {
    public abstract PaymentDAO paymentDAO();
    private static PaymentRoomDatabase INSTANCE;

    // Create instance
}
