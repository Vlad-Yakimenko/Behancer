package com.elegion.test.behancer.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.View;

import com.elegion.domain.model.user.User;
import com.elegion.domain.service.ProfileService;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileViewModel {

    private Disposable mDisposable;

    @Inject
    ProfileService mService;

    private MutableLiveData<Boolean> mIsErrorVisible = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    private LiveData<User> mUser;
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = this::obtainProfile;

    private View.OnClickListener mOnClickListener;
    private String mUsername;

    public void obtainProfile() {
        mDisposable = mService.getUser(mUsername)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> mIsLoading.postValue(true))
                .doOnSuccess(user -> mIsErrorVisible.postValue(false))
                .doFinally(() -> mIsLoading.postValue(false))
                .subscribe(
                        response -> mService.insertUser(response),
                        throwable -> mIsErrorVisible.postValue(true)
                );
    }

    public void dispatchDetach() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    public LiveData<Boolean> getIsErrorVisible() {
        return mIsErrorVisible;
    }

    public LiveData<Boolean> getIsLoading() {
        return mIsLoading;
    }

    public LiveData<User> getUser() {
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
        mUser = mService.getUserLive(mUsername);
    }

    public View.OnClickListener getOnClickListener() {
        return mOnClickListener;
    }

    public void setOnClickListener(View.OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }
}
