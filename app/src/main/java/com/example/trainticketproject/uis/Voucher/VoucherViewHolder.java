package com.example.trainticketproject.uis.Voucher;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainticketproject.R;

public class VoucherViewHolder extends RecyclerView.ViewHolder {
    TextView tvVoucherName, tvVoucherDesc, tvRewardPoint, btnGetVoucher;
    public VoucherViewHolder(@NonNull View itemView) {
        super(itemView);

        tvVoucherName = itemView.findViewById(R.id.tvVoucherName);
        tvVoucherDesc = itemView.findViewById(R.id.tvVoucherDesc);
        tvRewardPoint = itemView.findViewById(R.id.tvRewardPoint);
        btnGetVoucher = itemView.findViewById(R.id.btnGetVoucher);
    }
}
