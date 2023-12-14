package com.example.trainticketproject.uis.Ticket;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainticketproject.R;

public class MyTicketViewHolder extends RecyclerView.ViewHolder {
    TextView tvTicketTrip, tvTicketPrice, tvTicketPurchasedDate, tvTicketSeatNumber;
    public MyTicketViewHolder(@NonNull View itemView) {
        super(itemView);

        tvTicketTrip = itemView.findViewById(R.id.tvTicketTrip);
        tvTicketPrice = itemView.findViewById(R.id.tvTicketPrice);
        tvTicketPurchasedDate = itemView.findViewById(R.id.tvTicketPurchasedDate);
        tvTicketSeatNumber = itemView.findViewById(R.id.tvTicketSeatNumber);
    }
}
