package com.elegion.test.behancer.ui.profile;

import com.arellomobile.mvp.InjectViewState;
import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.di.ProfileFragment.ProfileFragmentComponent;
import com.example.domain.service.ProfileService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class ProfilePresenter extends BasePresenter<ProfileView> {

    @Inject
    ProfileView mView;
    @Inject
    ProfileService mService;

    public ProfilePresenter(ProfileFragmentComponent component) {
        component.inject(this);
    }

    public void getProfile(String username) {
        mComposeDisposable.add(
                mService.getUser(username)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> mView.showRefresh())
                        .doFinally(() -> mView.hideRefresh())
                        .subscribe(
                                response -> mView.showProfile(response),
                                throwable -> mView.showError()));
    }
}
