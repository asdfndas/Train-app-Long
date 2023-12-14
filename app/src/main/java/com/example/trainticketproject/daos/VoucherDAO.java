package com.example.trainticketproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.trainticketproject.models.Voucher;
import com.example.trainticketproject.models.VoucherWithUsers;

import java.util.List;

@Dao
public interface VoucherDAO {
    @Insert
    void insertVoucher(Voucher voucher);

    @Insert
    void insertMultipleVouchers(List<Voucher> vouchers);

    @Query("SELECT * FROM Voucher")
    LiveData<List<Voucher>> getAllVouchers();
}
