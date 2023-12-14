package com.example.trainticketproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.trainticketproject.models.Gender;
import com.example.trainticketproject.models.User;
import com.example.trainticketproject.models.UserWithVouchers;
import com.example.trainticketproject.models.VoucherWithUsers;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM User WHERE uid = :uid")
    LiveData<User> getUserById(Long uid);

    @Insert
    void insertMultipleUser(List<User> users);

    @Query("UPDATE User SET rewardPoint = :newRewardPoint WHERE uid = :uid")
    void updateRewardPoint(int newRewardPoint, Long uid);

    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT COUNT(*) FROM User WHERE username = :username")
    int getIntCountByUsername(String username);

    @Query("SELECT COUNT(*) FROM User WHERE username = :username")
    LiveData<Integer> getCountWithUsername(String username);

    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
    LiveData<User> getLoginUser(String username, String password);

    @Query("UPDATE User SET username = :username, fullname = :fullname, age = :age, gender = :gender, email = :email, password = :password, phone = :phone WHERE uid = :userId")
    void updateUserById(long userId, String username,String fullname, int age, Gender gender, String email, String password, String phone);

}
