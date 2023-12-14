package com.example.trainticketproject.uis.Ticket;

import android.content.Intent;
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
import com.example.trainticketproject.models.Ticket;
import com.example.trainticketproject.utils.DateTimeConverter;
import com.example.trainticketproject.viewmodels.TicketViewModel;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class MyTicketAdapter extends RecyclerView.Adapter<MyTicketViewHolder> {
    List<Ticket> myTickets;
    private TicketViewModel ticketViewModel;

    public MyTicketAdapter(List<Ticket> myTickets, TicketViewModel ticketViewModel) {
        this.myTickets = myTickets;
        this.ticketViewModel = ticketViewModel;
    }

    @NonNull
    @Override
    public MyTicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_ticket_item, parent, false);
        return new MyTicketViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTicketViewHolder holder, int position) {
        if (myTickets == null || myTickets.size() == 0) {
            Toast.makeText(holder.itemView.getContext(), "There is no tickets yet", Toast.LENGTH_SHORT).show();
        } else {
            Ticket myTicket = myTickets.get(position);

            String formattedTime = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                formattedTime = myTicket.getIssuedDate().format(DateTimeFormatter.ofPattern("HH:mm"));
            }

            String formattedDate = DateTimeConverter.formatFullDate(myTicket.getIssuedDate());
            holder.tvTicketPurchasedDate.setText(formattedTime + ", " + formattedDate);

            ticketViewModel.getTrainById(myTicket.getTrainId()).observe((LifecycleOwner) holder.itemView.getContext(), train -> {
                holder.tvTicketTrip.setText(train.getTrip());

                NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
                String priceString = numberFormat.format(train.getPrice());
                holder.tvTicketPrice.setText(priceString + " VND");
            });

            ticketViewModel.getSeatById(myTicket.getSeatNumber()).observe((LifecycleOwner) holder.itemView.getContext(), seat -> {
                Log.d("seat_id", seat.getId() + "_" + seat.getTicketId() + "_" + seat.getTrainId() + "_" + seat.getUid());
                holder.tvTicketSeatNumber.setText(String.valueOf(seat.getSeatCode()));
            });

            holder.itemView.setOnClickListener(v -> {
                Intent detailTicketIntent = new Intent(v.getContext(), DetailTicketActivity.class);
                detailTicketIntent.putExtra("ticketId", myTicket.getTicketId());

                v.getContext().startActivity(detailTicketIntent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return myTickets == null ? 0 : myTickets.size();
    }
}
