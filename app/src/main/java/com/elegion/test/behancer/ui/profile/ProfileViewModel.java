package com.elegion.test.behancer.ui.profile;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v4.widget.SwipeRefreshLayout;

import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.model.user.User;
import com.elegion.test.behancer.utils.ApiUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileViewModel {

    private Disposable mDisposable;
    private Storage mStorage;

    private ObservableBoolean mIsErrorVisible = new ObservableBoolean(false);
    private ObservableBoolean mIsLoading = new ObservableBoolean(false);
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = this::obtainProfile;

    private String mUsername;
    private ObservableField<User> mUser = new ObservableField<>();

    public ProfileViewModel(Storage mStorage) {
        this.mStorage = mStorage;
    }

    public void obtainProfile() {
        mDisposable = ApiUtils.getApiService().getUserInfo(mUsername)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(response -> mStorage.insertUser(response))
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                                mStorage.getUser(mUsername) :
                                null)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mIsLoading.set(true))
                .doFinally(() -> mIsLoading.set(false))
                .subscribe(
                        response -> {
                            mIsErrorVisible.set(false);
                            mUser.set(response.getUser());
                        },
                        throwable -> mIsErrorVisible.set(true));
    }

    public void dispatchDetach() {
        mStorage = null;
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    public boolean getIsErrorVisible() {
        return mIsErrorVisible.get();
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public ObservableField<User> getUser() {
        return mUser;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

//    private void bind(User user) {
//        Picasso.with(getContext())
//                .load(user.getImage().getPhotoUrl())
//                .fit()
//                .into(mProfileImage);
//        mProfileName.setText(user.getDisplayName());
//        mProfileCreatedOn.setText(DateUtils.format(user.getCreatedOn()));
//        mProfileLocation.setText(user.getLocation());
//    }
}
