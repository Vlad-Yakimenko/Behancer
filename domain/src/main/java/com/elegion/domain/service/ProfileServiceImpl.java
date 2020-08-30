package com.elegion.domain.service;

import androidx.lifecycle.LiveData;

import com.elegion.domain.ApiUtils;
import com.elegion.domain.model.user.User;
import com.elegion.domain.repository.ProfileRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class ProfileServiceImpl implements ProfileService {

    @Inject
    @Named(ProfileRepository.SERVER)
    ProfileRepository mServerRepository;

    @Inject
    @Named(ProfileRepository.DB)
    ProfileRepository mDBRepository;

    @Override
    public Single<User> getUser(String username) {
        return mServerRepository.getUser(username)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(mDBRepository::insertUser)
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass())
                                ? (mDBRepository.getUser(username) != null ? mDBRepository.getUser(username).blockingGet() : null)
                                : null);
    }

    @Override
    public LiveData<User> getUserLive(String username) {
        return mDBRepository.getUserLive(username);
    }

    @Override
    public void insertUser(User user) {
        mDBRepository.insertUser(user);
    }
}
