package com.example.trainticketproject.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.trainticketproject.daos.UserVoucherDAO;
import com.example.trainticketproject.databases.UserVoucherRoomDatabase;
import com.example.trainticketproject.models.User;
import com.example.trainticketproject.models.UserVoucherCrossRef;
import com.example.trainticketproject.models.UserWithVouchers;
import com.example.trainticketproject.models.Voucher;

import java.util.List;

public class UserVoucherRepository {
    private final UserVoucherDAO userVoucherDAO;

    public UserVoucherRepository(Application application) {
        UserVoucherRoomDatabase userVoucherRoomDatabase = UserVoucherRoomDatabase.getDatabase(application);
        userVoucherDAO = userVoucherRoomDatabase.userVoucherDAO();
    }

    public LiveData<UserWithVouchers> getUserWithVouchers(Long uid) {
        return userVoucherDAO.getUserWithVouchers(uid);
    }
}
