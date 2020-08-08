package com.elegion.test.behancer.ui.projects;

import com.arellomobile.mvp.InjectViewState;
import com.elegion.test.behancer.common.BasePresenter;
import com.example.domain.service.ProjectService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class ProjectsPresenter extends BasePresenter<ProjectsView> {

    protected ProjectsView mView;

    @Inject
    ProjectService mService;

    @Inject
    public ProjectsPresenter() {
    }

    public void setView(ProjectsView view) {
        mView = view;
    }

    public void getProjects() {
        mComposeDisposable.add(
                mService.getProjects()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> mView.showRefresh())
                        .doFinally(() -> mView.hideRefresh())
                        .subscribe(
                                response -> mView.showProjects(response),
                                throwable -> mView.showError()));
    }

    public void openProfileFragment(String username) {
        mView.openProfileFragment(username);
    }
}
