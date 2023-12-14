package com.example.trainticketproject.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.trainticketproject.daos.SeatDAO;
import com.example.trainticketproject.daos.TicketDAO;
import com.example.trainticketproject.daos.TrainDAO;
import com.example.trainticketproject.daos.UserDAO;
import com.example.trainticketproject.models.Seat;
import com.example.trainticketproject.models.Ticket;
import com.example.trainticketproject.models.Train;
import com.example.trainticketproject.models.User;
import com.example.trainticketproject.utils.DateTimeConverter;

@Database(entities = { Ticket.class, User.class, Seat.class, Train.class }, version = 1)
@TypeConverters({ DateTimeConverter.class })
public abstract class TicketRoomDatabase extends RoomDatabase {
    public abstract TicketDAO ticketDAO();
    public abstract UserDAO userDAO();
    public abstract TrainDAO trainDAO();
    public abstract SeatDAO seatDAO();
    private static TicketRoomDatabase INSTANCE;

    public static TicketRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TicketRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            TicketRoomDatabase.class,
                            "ticket_database"
                    ).build();
                }
            }
        }

        return INSTANCE;
    }
}
