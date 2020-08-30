package com.elegion.test.behancer.data.repository;

import androidx.lifecycle.LiveData;

import com.elegion.domain.model.user.User;
import com.elegion.domain.model.user.UserResponse;
import com.elegion.domain.repository.ProfileRepository;
import com.elegion.test.behancer.data.api.BehanceApi;

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
