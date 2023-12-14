package com.example.trainticketproject.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.trainticketproject.models.User;
import com.example.trainticketproject.models.UserVoucherCrossRef;
import com.example.trainticketproject.models.UserWithVouchers;
import com.example.trainticketproject.models.Voucher;
import com.example.trainticketproject.repositories.UserVoucherCrossRefRepository;
import com.example.trainticketproject.repositories.UserVoucherRepository;

import java.util.List;

public class UserVoucherViewModel extends AndroidViewModel {
    private UserVoucherRepository userVoucherRepository;
    private UserVoucherCrossRefRepository userVoucherCrossRefRepository;
    public UserVoucherViewModel(@NonNull Application application) {
        super(application);
        userVoucherRepository = new UserVoucherRepository(application);
        userVoucherCrossRefRepository = new UserVoucherCrossRefRepository(application);
    }

    public LiveData<UserWithVouchers> getUserWithVouchers(Long uid) {
        return userVoucherRepository.getUserWithVouchers(uid);
    }

    public void insertVoucherForUser(UserVoucherCrossRef userVoucherCrossRef) {
        userVoucherCrossRefRepository.insertVoucherForUser(userVoucherCrossRef);
    }

    public LiveData<List<Voucher>> getVouchersForUser(Long uid) {
        return userVoucherCrossRefRepository.getVouchersForUser(uid);
    }

    public void insertUser(User user) {
        userVoucherCrossRefRepository.insertUser(user);
    }

    public void insertVoucher(Voucher voucher) {
        userVoucherCrossRefRepository.insertVoucher(voucher);
    }
}
