package com.example.trainticketproject.uis.Voucher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.trainticketproject.R;
import com.example.trainticketproject.models.User;
import com.example.trainticketproject.models.Voucher;
import com.example.trainticketproject.uis.Train.TrainActivity;
import com.example.trainticketproject.viewmodels.UserViewModel;
import com.example.trainticketproject.viewmodels.UserVoucherViewModel;
import com.example.trainticketproject.viewmodels.VoucherViewModel;

import java.util.ArrayList;
import java.util.List;

public class VoucherActivity extends AppCompatActivity {
    Long uid;
    private UserViewModel userViewModel;
    private VoucherViewModel voucherViewModel;
    private UserVoucherViewModel userVoucherViewModel;

    private VoucherAdapter voucherAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        voucherViewModel = new ViewModelProvider(this).get(VoucherViewModel.class);
        userVoucherViewModel = new ViewModelProvider(this).get(UserVoucherViewModel.class);

        recyclerView = findViewById(R.id.recyclerView);

//        new Thread(() -> {
//            List<Voucher> vouchers = createSampleVoucher();
//            voucherViewModel.insertMultipleVouchers(vouchers);
//        }).start();

//        Add sample data
//        new Thread(() -> {
//            List<User> users = TrainActivity.createSampleUser();
//
//            userViewModel.insertMultipleUser(users);
//        }).start();

        Intent allVoucherIntent = getIntent();
        uid = allVoucherIntent.getLongExtra("uid", 1L);

        voucherViewModel.getAllVouchers().observe(this, vouchers -> {
            userViewModel.getUserById(uid).observe(this, user -> {
                if (user != null) {
                    userVoucherViewModel.getVouchersForUser(uid).observe(this, ownedVoucher -> {
                        voucherAdapter = new VoucherAdapter(user, vouchers, ownedVoucher, userVoucherViewModel, userViewModel);
                        recyclerView.setAdapter(voucherAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    });
                } else {
                    Toast.makeText(this, "Invalid user", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private List<Voucher> createSampleVoucher() {
        List<Voucher> vouchers = new ArrayList<>();

        vouchers.add(new Voucher("Discount 20%", "Good", 200));
        vouchers.add(new Voucher("Discount 3%", "Good", 200));
        vouchers.add(new Voucher("Discount 45%", "Good", 200));
        vouchers.add(new Voucher("Discount 22%", "Good", 200));

        return vouchers;
    }
}