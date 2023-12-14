package com.example.trainticketproject.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.trainticketproject.models.Voucher;
import com.example.trainticketproject.repositories.VoucherRepository;

import java.util.List;

public class VoucherViewModel extends AndroidViewModel {
    private final VoucherRepository voucherRepository;

    public VoucherViewModel(@NonNull Application application) {
        super(application);
        voucherRepository = new VoucherRepository(application);
    }


    public void insertVoucher(Voucher voucher) {
        voucherRepository.insertVoucher(voucher);
    }

    public void insertMultipleVouchers(List<Voucher> vouchers) {
        voucherRepository.insertMultipleVouchers(vouchers);
    }

    public LiveData<List<Voucher>> getAllVouchers() {
        return voucherRepository.getAllVouchers();
    }
}
