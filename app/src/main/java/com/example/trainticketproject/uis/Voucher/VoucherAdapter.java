package com.example.trainticketproject.uis.Voucher;

import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainticketproject.R;
import com.example.trainticketproject.models.User;
import com.example.trainticketproject.models.UserVoucherCrossRef;
import com.example.trainticketproject.models.Voucher;
import com.example.trainticketproject.viewmodels.UserViewModel;
import com.example.trainticketproject.viewmodels.UserVoucherViewModel;

import java.util.ArrayList;
import java.util.List;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherViewHolder> {
    private List<Voucher> vouchers;
    private User user;
    private List<Voucher> ownedVouchers;
    private UserVoucherViewModel userVoucherViewModel;
    private UserViewModel userViewModel;
    public VoucherAdapter(User user, List<Voucher> vouchers, List<Voucher> ownedVouchers, UserVoucherViewModel userVoucherViewModel, UserViewModel userViewModel) {
        this.user = user;
        this.vouchers = vouchers;
        this.ownedVouchers = ownedVouchers;
        this.userVoucherViewModel = userVoucherViewModel;
        this.userViewModel = userViewModel;
    }

    @NonNull
    @Override
    public VoucherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.voucher_item, parent, false);
        return new VoucherViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherViewHolder holder, int position) {
        Voucher voucher = vouchers.get(position);

        boolean isUserHasVoucher;

        if (ownedVouchers == null) {
            isUserHasVoucher = checkUserHasVoucher(new ArrayList<>(), voucher);
        } else {
            isUserHasVoucher = checkUserHasVoucher(ownedVouchers, voucher);
        }

        holder.tvVoucherName.setText(voucher.getVoucherName());
        holder.tvVoucherDesc.setText(voucher.getDescription());
        holder.tvRewardPoint.setText(String.valueOf(voucher.getPoint()) + " Points");

        if (isUserHasVoucher) {
            holder.btnGetVoucher.setText("Received");
            holder.btnGetVoucher.setEnabled(false);
        } else {
            holder.btnGetVoucher.setText("Get Voucher");
            holder.btnGetVoucher.setEnabled(true);

            holder.btnGetVoucher.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Confirm");
                builder.setMessage("Do you want to get this voucher?");
                builder.setPositiveButton("Yes", (dialog, which) -> {
                    if (user.getRewardPoint() >= voucher.getPoint()) {
                        new Thread(() -> {
                            try {
                                user.setRewardPoint(user.getRewardPoint() - voucher.getPoint());

                                userVoucherViewModel.insertVoucher(voucher);
                                userVoucherViewModel.insertUser(user);

                                UserVoucherCrossRef usrVoucherRef = new UserVoucherCrossRef();
                                usrVoucherRef.voucherId = voucher.getVoucherId();
                                usrVoucherRef.uid = user.getUid();

                                try {
                                    userVoucherViewModel.insertVoucherForUser(usrVoucherRef);
                                    userViewModel.updateRewardPoint(user.getRewardPoint(), user.getUid());

                                    v.post(() -> {
                                        notifyDataSetChanged();
                                        Toast.makeText(v.getContext(), "The voucher has been added to your account! Your points remain " + user.getRewardPoint(), Toast.LENGTH_SHORT).show();
                                    });
                                } catch (Exception ex) {
                                    Log.d("INSERT FAILED", ex.getMessage());
                                }
                            } catch (Exception ex) {
                                Log.d("ERROR DATABASE", ex.getMessage());
                                ex.printStackTrace();
                            }
                        }).start();
                    } else {
                        Toast.makeText(v.getContext(), "Not enough points", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
                builder.create().show();
            });
        }
    }

    private boolean checkUserHasVoucher(List<Voucher> ownedVouchers, Voucher voucher) {
        if (ownedVouchers == null || ownedVouchers.size() == 0) {
            return false;
        }

        return ownedVouchers.stream().anyMatch(v -> v.getVoucherId() == voucher.getVoucherId());
    }

    @Override
    public int getItemCount() {
        return vouchers != null ? vouchers.size() : 0;
    }
}
