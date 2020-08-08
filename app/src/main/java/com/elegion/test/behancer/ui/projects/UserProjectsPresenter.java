package com.elegion.test.behancer.ui.projects;

import com.elegion.test.behancer.AppDelegate;
import com.example.domain.ApiUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserProjectsPresenter extends ProjectsPresenter {

    private String mUsername;

    public UserProjectsPresenter(String username) {
        this.mUsername = username;
        AppDelegate.getAppComponent().inject(this);
    }

    @Override
    public void getProjects() {
        mComposeDisposable.add(
                mService.getUserProjects(mUsername)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> mView.showRefresh())
                        .doFinally(() -> mView.hideRefresh())
                        .subscribe(
                                response -> mView.showProjects(response),
                                throwable -> mView.showError()));
    }
}
