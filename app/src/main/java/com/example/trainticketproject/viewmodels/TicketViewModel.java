package com.example.trainticketproject.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.trainticketproject.models.Seat;
import com.example.trainticketproject.models.Ticket;
import com.example.trainticketproject.models.Train;
import com.example.trainticketproject.models.User;
import com.example.trainticketproject.repositories.TicketRepository;

import java.util.List;

public class TicketViewModel extends AndroidViewModel {
    private final TicketRepository ticketRepository;
    public TicketViewModel(@NonNull Application application) {
        super(application);
        ticketRepository = new TicketRepository(application);
    }

    public LiveData<Integer> getAvailableTicketCountByTrainId(Long trainId) {
        return ticketRepository.getAvailableTicketCountByTrainId(trainId);
    }

    public LiveData<Ticket> getTicketById(Long ticketId) {
        return ticketRepository.getTicketById(ticketId);
    }

    public Long insertTicket(Ticket ticket) {
        return ticketRepository.insertTicket(ticket);
    }

    public Long insertUser(User user) {
        return ticketRepository.insertUser(user);
    }

    public Long insertSeat(Seat seat) {
        return ticketRepository.insertSeat(seat);
    }

    public Long insertTrain(Train train) {
        return ticketRepository.insertTrain(train);
    }

    public LiveData<List<Ticket>> getTicketsForUser(Long uid) {
        return ticketRepository.getTicketsForUser(uid);
    }

    public LiveData<Train> getTrainById(Long trainId) {
        return ticketRepository.getTrainById(trainId);
    }

    public LiveData<Seat> getSeatById(Long seatId) {
        return ticketRepository.getSeatById(seatId);
    }
}
