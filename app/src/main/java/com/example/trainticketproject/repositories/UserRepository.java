package com.example.trainticketproject.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import com.example.trainticketproject.daos.UserDAO;
import com.example.trainticketproject.databases.UserRoomDatabase;
import com.example.trainticketproject.models.Gender;
import com.example.trainticketproject.models.User;
import com.example.trainticketproject.models.UserWithVouchers;

import java.util.List;

public class UserRepository {
    private final UserDAO userDAO;

    public UserRepository(Application application) {
        UserRoomDatabase userRoomDatabase = UserRoomDatabase.getDatabase(application);
        userDAO = userRoomDatabase.userDAO();
    }

    public void insert(User user) {
        userDAO.insert(user);
    }

    public LiveData<User> getUserById(Long uid) {
        return userDAO.getUserById(uid);
    }

    public void insertMultipleUser(List<User> users) {
        userDAO.insertMultipleUser(users);
    }

    public void updateRewardPoint(int newRewardPoint, Long uid) {
        userDAO.updateRewardPoint(newRewardPoint, uid);
    }

    public LiveData<List<User>> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public LiveData<Integer> getCountWithUsername(String username) {
        return userDAO.getCountWithUsername(username);
    }
    public int getIntCountByUsername(String username) {
        return userDAO.getIntCountByUsername(username);
    }
    public LiveData<User> getLoginUser(String username, String password) {
        return userDAO.getLoginUser(username, password);
    }

    public void updateUserById(long userId, String username, String fullname, int age, Gender gender, String email, String password, String phone) {
        userDAO.updateUserById(userId, username,fullname, age, gender, email, password, phone);
    }
}
