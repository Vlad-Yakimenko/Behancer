package com.elegion.test.behancer.data_utils.repository;

import com.elegion.test.behancer.data_utils.database.BehanceDao;
import com.example.domain.model.user.Image;
import com.example.domain.model.user.User;

import java.util.NoSuchElementException;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Single;

public class ProfileDBRepository implements ProfileRepository {

    @Inject
    BehanceDao mBehanceDao;

    @Override
    public Single<User> getUser(String username) {
        final User user = mBehanceDao.getUserByName(username);
        if (user == null) return null;

        Image image = mBehanceDao.getImageFromUser(user.getId());
        user.setImage(image);
        return Single.fromCallable(() -> user);
    }

    @Override
    public void insertUser(User user) {
        Image image = user.getImage();
        image.setId(user.getId());
        image.setUserId(user.getId());

        mBehanceDao.insertUser(user);
        mBehanceDao.insertImage(image);
    }
}
