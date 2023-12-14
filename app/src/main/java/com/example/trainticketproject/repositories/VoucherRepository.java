package com.example.trainticketproject.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.trainticketproject.daos.VoucherDAO;
import com.example.trainticketproject.databases.VoucherRoomDatabase;
import com.example.trainticketproject.models.Voucher;

import java.util.List;

public class VoucherRepository {
    private final VoucherDAO voucherDAO;

    public VoucherRepository(Application application) {
        VoucherRoomDatabase voucherRoomDatabase = VoucherRoomDatabase.getDatabase(application);
        voucherDAO = voucherRoomDatabase.voucherDAO();
    }

    public void insertVoucher(Voucher voucher) {
        voucherDAO.insertVoucher(voucher);
    }

    public void insertMultipleVouchers(List<Voucher> vouchers) {
        voucherDAO.insertMultipleVouchers(vouchers);
    }

    public LiveData<List<Voucher>> getAllVouchers() {
        return voucherDAO.getAllVouchers();
    }
}
