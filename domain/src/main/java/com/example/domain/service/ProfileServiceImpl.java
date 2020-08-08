package com.example.domain.service;

import com.example.domain.ApiUtils;
import com.example.domain.model.user.User;
import com.example.domain.repository.ProfileRepository;

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

    @Inject
    public ProfileServiceImpl() {
    }

    @Override
    public Single<User> getUser(String username) {
        return mServerRepository.getUser(username)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(mDBRepository::insertUser)
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass())
                                ? mDBRepository.getUser(username).blockingGet()
                                : null);
    }

    @Override
    public void insertUser(User user) {
        mDBRepository.insertUser(user);
    }
}
