package com.example.trainticketproject.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class Voucher {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long voucherId;
    private String voucherName;
    private String description;
    private int point;
    public Voucher(String voucherName, String description, int point) {
        this.voucherName = voucherName;
        this.description = description;
        this.point = point;
    }

    public Voucher() {
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
