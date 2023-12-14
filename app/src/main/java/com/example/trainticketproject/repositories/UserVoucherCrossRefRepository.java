package com.example.trainticketproject.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.trainticketproject.daos.UserVoucherCrossRefDAO;
import com.example.trainticketproject.databases.UserVoucherRoomDatabase;
import com.example.trainticketproject.models.User;
import com.example.trainticketproject.models.UserVoucherCrossRef;
import com.example.trainticketproject.models.Voucher;

import java.util.List;

public class UserVoucherCrossRefRepository {
    private UserVoucherCrossRefDAO userVoucherCrossRefDAO;

    public UserVoucherCrossRefRepository(Application application) {
        userVoucherCrossRefDAO = UserVoucherRoomDatabase.getDatabase(application).userVoucherCrossRefDAO();
    }

    public void insertVoucherForUser(UserVoucherCrossRef userVoucherCrossRef) {
        userVoucherCrossRefDAO.insertVoucherForUser(userVoucherCrossRef);
    }

    public LiveData<List<Voucher>> getVouchersForUser(Long uid) {
        return userVoucherCrossRefDAO.getVouchersForUser(uid);
    }

    public void insertUser(User user) {
        userVoucherCrossRefDAO.insertUser(user);
    }

    public void insertVoucher(Voucher voucher) {
        userVoucherCrossRefDAO.insertVoucher(voucher);
    }
}
