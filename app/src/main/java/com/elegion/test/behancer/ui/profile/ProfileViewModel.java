package com.elegion.test.behancer.ui.profile;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.example.domain.model.user.User;
import com.elegion.test.behancer.data_utils.service.ProfileService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileViewModel {

    private Disposable mDisposable;

    @Inject
    ProfileService mService;

    private ObservableBoolean mIsErrorVisible = new ObservableBoolean(false);
    private ObservableBoolean mIsLoading = new ObservableBoolean(false);
    private ObservableField<User> mUser = new ObservableField<>();
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = this::obtainProfile;

    private View.OnClickListener mOnClickListener;
    private String mUsername;

    public void obtainProfile() {
        mDisposable = mService.getUser(mUsername)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mIsLoading.set(true))
                .doOnSuccess(user -> mIsErrorVisible.set(false))
                .doFinally(() -> mIsLoading.set(false))
                .subscribe(
                        response -> mUser.set(response),
                        throwable -> mIsErrorVisible.set(true)
                );
    }

    public void dispatchDetach() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    public ObservableBoolean getIsErrorVisible() {
        return mIsErrorVisible;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public ObservableField<User> getUser() {
        return mUser;
    }

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return mOnRefreshListener;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public View.OnClickListener getOnClickListener() {
        return mOnClickListener;
    }

    public void setOnClickListener(View.OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }
}
