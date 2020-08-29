package com.elegion.test.behancer.data_utils.repository;

import androidx.lifecycle.LiveData;

import com.example.domain.model.user.User;

import io.reactivex.Single;

public interface ProfileRepository {

    String SERVER = "SERVER";
    String DB = "DB";

    Single<User> getUser(String username);

    LiveData<User> getUserLive(String username);

    void insertUser(User user);
}
