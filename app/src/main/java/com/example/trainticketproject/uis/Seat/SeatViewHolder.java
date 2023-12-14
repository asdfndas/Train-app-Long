package com.example.trainticketproject.uis.Seat;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.trainticketproject.R;

public class SeatViewHolder extends RecyclerView.ViewHolder {
    public final TextView tvSeatCode;

    public SeatViewHolder(View itemView) {
        super(itemView);
        tvSeatCode = itemView.findViewById(R.id.tvSeatCode);
    }
}
