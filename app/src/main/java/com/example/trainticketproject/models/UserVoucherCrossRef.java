package com.example.trainticketproject.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "user_voucher"
//        primaryKeys = {"uid", "voucherId"},
//        indices = {@Index(value = {"uid", "voucherId"}, unique = true)},
//        foreignKeys = {
//                @ForeignKey(entity = User.class,
//                        parentColumns = "uid",
//                        childColumns = "uid",
//                        onDelete = ForeignKey.CASCADE),
//                @ForeignKey(entity = Voucher.class,
//                        parentColumns = "voucherId",
//                        childColumns = "voucherId",
//                        onDelete = ForeignKey.CASCADE)
//        }
        )
public class UserVoucherCrossRef {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @NonNull
    public Long uid;
    @NonNull
    public Long voucherId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
