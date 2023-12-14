package com.example.trainticketproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.trainticketproject.models.User;
import com.example.trainticketproject.models.UserVoucherCrossRef;
import com.example.trainticketproject.models.Voucher;

import java.util.List;

@Dao
public interface UserVoucherCrossRefDAO {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertVoucher(Voucher voucher);

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertVoucherForUser(UserVoucherCrossRef userVoucherCrossRef);

    @Query("SELECT * FROM Voucher " +
            "INNER JOIN user_voucher ON Voucher.voucherId = user_voucher.voucherId " +
            "WHERE user_voucher.uid = :uid")
    LiveData<List<Voucher>> getVouchersForUser(Long uid);
}
