package com.elegion.test.behancer.data_utils.service;

import com.example.domain.model.user.User;

import io.reactivex.Single;

public interface ProfileService {

    Single<User> getUser(String username);

    void insertUser(User user);
}
