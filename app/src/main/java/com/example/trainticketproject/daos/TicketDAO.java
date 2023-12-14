package com.example.trainticketproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.trainticketproject.models.Seat;
import com.example.trainticketproject.models.Ticket;
import com.example.trainticketproject.models.Train;
import com.example.trainticketproject.models.User;

import java.util.List;

@Dao
public interface TicketDAO {
    @Query("SELECT COUNT(*) FROM Ticket WHERE trainId = :trainId AND status = 'AVAILABLE'")
    LiveData<Integer> getAvailableTicketCountByTrainId(Long trainId);

    @Query("SELECT * FROM Ticket WHERE ticketId = :id")
    LiveData<Ticket> getTicketById(Long id);

    @Query("SELECT * FROM Ticket WHERE uid = :uid")
    LiveData<List<Ticket>> getTicketsForUser(Long uid);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insertTicket(Ticket ticket);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insertUser(User user);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insertSeat(Seat seat);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insertTrain(Train train);

    @Insert
    void insertMultipleTickets(List<Ticket> sampleTickets);
}
