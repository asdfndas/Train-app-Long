package com.example.trainticketproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.trainticketproject.models.User;
import com.example.trainticketproject.models.UserVoucherCrossRef;
import com.example.trainticketproject.models.UserWithVouchers;
import com.example.trainticketproject.models.Voucher;
import com.example.trainticketproject.models.VoucherWithUsers;

import java.util.List;

@Dao
public interface UserVoucherDAO {
    @Transaction
    @Query("SELECT * FROM User WHERE uid = :uid")
    LiveData<UserWithVouchers> getUserWithVouchers(Long uid);

    @Transaction
    @Insert
    void insertUserWithVoucher(Voucher voucher);
}
