package com.elegion.test.behancer.data_utils.repository;

import androidx.lifecycle.LiveData;

import com.elegion.test.behancer.data_utils.database.BehanceDao;
import com.example.domain.model.user.Image;
import com.example.domain.model.user.User;

import javax.inject.Inject;

import io.reactivex.Single;

public class ProfileDBRepository implements ProfileRepository {

    @Inject
    BehanceDao mBehanceDao;

    @Override
    public Single<User> getUser(String username) {
        final User user = mBehanceDao.getUserByName(username);
        if (user == null) return null;

        return Single.fromCallable(() -> user);
    }

    @Override
    public LiveData<User> getUserLive(String username) {
        return mBehanceDao.getUserByNameLive(username);
    }

    @Override
    public void insertUser(User user) {
        Image image = user.getImage();
        image.setId(user.getId());

        mBehanceDao.insertUser(user);
        mBehanceDao.insertImage(image);
    }
}
