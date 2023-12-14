package com.example.trainticketproject.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.trainticketproject.daos.SeatDAO;
import com.example.trainticketproject.daos.TicketDAO;
import com.example.trainticketproject.daos.TrainDAO;
import com.example.trainticketproject.databases.TicketRoomDatabase;
import com.example.trainticketproject.models.Seat;
import com.example.trainticketproject.models.Ticket;
import com.example.trainticketproject.models.Train;
import com.example.trainticketproject.models.User;

import java.util.List;

public class TicketRepository {
    private final TicketDAO ticketDAO;
    private final TrainDAO trainDAO;
    private final SeatDAO seatDAO;

    public TicketRepository(Application application) {
        TicketRoomDatabase ticketDatabase = TicketRoomDatabase.getDatabase(application);
        ticketDAO = ticketDatabase.ticketDAO();
        trainDAO = ticketDatabase.trainDAO();
        seatDAO = ticketDatabase.seatDAO();
    }

    public LiveData<Integer> getAvailableTicketCountByTrainId(Long trainId) {
        return ticketDAO.getAvailableTicketCountByTrainId(trainId);
    }

    public LiveData<Ticket> getTicketById(Long ticketId) {
        return ticketDAO.getTicketById(ticketId);
    }

    public Long insertTicket(Ticket ticket) {
        return ticketDAO.insertTicket(ticket);
    }

    public Long insertUser(User user) {
        return ticketDAO.insertUser(user);
    }

    public Long insertSeat(Seat seat) {
        return ticketDAO.insertSeat(seat);
    }

    public Long insertTrain(Train train) {
        return ticketDAO.insertTrain(train);
    }

    public LiveData<List<Ticket>> getTicketsForUser(Long uid) {
        return ticketDAO.getTicketsForUser(uid);
    }

    public LiveData<Train> getTrainById(Long trainId) {
        return trainDAO.getTrainById(trainId);
    }

    public LiveData<Seat> getSeatById(Long seatId) {
        return seatDAO.getSeatById(seatId);
    }
}
