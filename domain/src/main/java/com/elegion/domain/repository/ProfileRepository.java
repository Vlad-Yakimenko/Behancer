package com.elegion.domain.repository;

import androidx.lifecycle.LiveData;

import com.elegion.domain.model.user.User;

import io.reactivex.Single;

public interface ProfileRepository {

    String SERVER = "SERVER";
    String DB = "DB";

    Single<User> getUser(String username);

    LiveData<User> getUserLive(String username);

    void insertUser(User user);
}
