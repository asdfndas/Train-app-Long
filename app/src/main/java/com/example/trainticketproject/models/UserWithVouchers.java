package com.example.trainticketproject.models;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class UserWithVouchers {
    @Embedded
    public User user;

    @Relation(parentColumn = "uid", entityColumn = "voucherId", associateBy = @Junction(UserVoucherCrossRef.class))
    public List<Voucher> vouchers;
}
