package com.elegion.domain.service;

import androidx.lifecycle.LiveData;

import com.elegion.domain.model.user.User;

import io.reactivex.Single;

public interface ProfileService {

    Single<User> getUser(String username);

    LiveData<User> getUserLive(String username);

    void insertUser(User user);
}
