package com.elegion.test.behancer.data_utils.repository;

import androidx.lifecycle.LiveData;

import com.elegion.test.behancer.data_utils.api.BehanceApi;
import com.example.domain.model.user.User;
import com.example.domain.model.user.UserResponse;

import javax.inject.Inject;

import io.reactivex.Single;

public class ProfileServerRepository implements ProfileRepository {

    @Inject
    BehanceApi mApi;

    @Override
    public Single<User> getUser(String username) {
        return mApi.getUserInfo(username).map(UserResponse::getUser);
    }

    @Override
    public LiveData<User> getUserLive(String username) {
        throw new UnsupportedOperationException("Not yet implemented for ServerRepository");
    }

    @Override
    public void insertUser(User user) {
        // do nothing
    }
}
