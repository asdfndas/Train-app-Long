package com.example.trainticketproject.models;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class VoucherWithUsers {
    @Embedded
    public Voucher voucher;


    @Relation(parentColumn = "voucherId", entityColumn = "uid", associateBy = @Junction(UserVoucherCrossRef.class))
    public List<User> users;
}
