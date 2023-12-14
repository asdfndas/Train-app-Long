package com.example.trainticketproject.uis.Train;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainticketproject.R;

public class TrainViewHolder extends RecyclerView.ViewHolder {
    TextView tvTripBriefInfo, tvPriceBriefInfo, tvTimeBriefInfo, tvStations, tvAvailability;
    public TrainViewHolder(@NonNull View itemView) {
        super(itemView);

        tvTripBriefInfo = itemView.findViewById(R.id.tvTripBriefInfo);
        tvPriceBriefInfo = itemView.findViewById(R.id.tvPriceBriefInfo);
        tvTimeBriefInfo = itemView.findViewById(R.id.tvTimeBriefInfo);
        tvStations = itemView.findViewById(R.id.tvStations);
        tvAvailability = itemView.findViewById(R.id.tvAvailability);
    }
}
