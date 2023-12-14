package com.example.trainticketproject.uis.Seat;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainticketproject.R;
import com.example.trainticketproject.models.Seat;
import com.example.trainticketproject.models.Status;
import com.example.trainticketproject.models.Ticket;
import com.example.trainticketproject.utils.ScheduleNotification;
import com.example.trainticketproject.viewmodels.SeatViewModel;
import com.example.trainticketproject.viewmodels.TicketViewModel;
import com.example.trainticketproject.viewmodels.TrainViewModel;
import com.example.trainticketproject.viewmodels.UserViewModel;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class SeatAdapter extends RecyclerView.Adapter<SeatViewHolder> {
    private final List<Seat> seatList;
    private SeatViewModel seatViewModel;
    private TicketViewModel ticketViewModel;
    private TrainViewModel trainViewModel;
    private UserViewModel userViewModel;
    private Long uid;

    public SeatAdapter(Long uid, List<Seat> seatList, SeatViewModel seatViewModel, TicketViewModel ticketViewModel, TrainViewModel trainViewModel, UserViewModel userViewModel) {
        this.seatList = seatList;
        this.seatViewModel = seatViewModel;
        this.ticketViewModel = ticketViewModel;
        this.trainViewModel = trainViewModel;
        this.userViewModel = userViewModel;
        this.uid = uid;
    }

    @NonNull
    @Override
    public SeatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seat_item, parent, false);
        return new SeatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeatViewHolder holder, int position) {
        Seat seat = seatList.get(position);
        holder.tvSeatCode.setText(String.valueOf(seat.getSeatCode()));

        if (seat.getStatus() == Status.AVAILABLE) {
            holder.tvSeatCode.setBackgroundColor(Color.CYAN);
            holder.tvSeatCode.setOnClickListener(v -> {
                holder.tvSeatCode.setBackgroundColor(Color.LTGRAY);
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Book Ticket")
                        .setMessage("Do you want to book the seat " + seat.getSeatCode() + "?")
                        .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                userViewModel.getUserById(uid).observe((LifecycleOwner) v.getContext(), user -> {
                                    trainViewModel.getTrainById(seat.getTrainId()).observe((LifecycleOwner) v.getContext(), train -> {
                                        seat.setStatus(Status.UNAVAILABLE);
                                        seat.setUid(uid);

                                        new Thread(() -> {
                                            try {
                                                Long insertedUserId = ticketViewModel.insertUser(user);
                                                Long insertedTrainId = ticketViewModel.insertTrain(train);
                                                Long insertedSeatId = ticketViewModel.insertSeat(seat);

                                                Ticket ticket = new Ticket(uid, train.getTrainId(), seat.getId(), LocalDateTime.now(), Status.AVAILABLE);

                                                Long insertedTicketId = ticketViewModel.insertTicket(ticket);
                                                seat.setTicketId(insertedTicketId);

                                                seatViewModel.update(seat);

                                                v.post(() -> {
                                                    notifyDataSetChanged();


                                                        // Schedule the reminder 3 days before
    //                                                LocalDateTime departureTime = train.getDepartureTime();
    //                                                LocalDateTime reminderTime = departureTime.minusDays(3);
    //                                                long reminderTimeMillis = reminderTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    //                                                ScheduleNotification.scheduleNotification(v.getContext(), "Reminder", "Your departure is approaching", reminderTimeMillis);
                                                    long reminderTimeMillis = System.currentTimeMillis() + 10 * 1000;
                                                    ScheduleNotification.scheduleNotification(v.getContext(), "Reminder", "Your departure is approaching", reminderTimeMillis);


                                                    Toast.makeText(v.getContext(), "SUCCESS TO BOOK TICKET! THERE WILL BE A REMINDER 3 DAYS BEFORE!", Toast.LENGTH_SHORT).show();
                                                });
                                            } catch (Exception ex) {
                                                ex.printStackTrace();
                                                v.post(() -> {
                                                    holder.tvSeatCode.setBackgroundColor(Color.CYAN);
                                                    Toast.makeText(v.getContext(), "FAILED TO TAKE ADVANCE THE SEAT", Toast.LENGTH_SHORT).show();
                                                });
                                            }
                                        }).start();
                                    });
                                });
                            } else {
                                Toast.makeText(v.getContext(), "Failed to generate ticket", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, (dialog, which) -> {
                            holder.tvSeatCode.setBackgroundColor(Color.CYAN);
                            dialog.dismiss();
                        })
                        .show();
            });
        } else {
            holder.tvSeatCode.setBackgroundColor(Color.RED);
            holder.tvSeatCode.setClickable(false);
        }
    }

    @Override
    public int getItemCount() {
        return seatList.size();
    }
}
