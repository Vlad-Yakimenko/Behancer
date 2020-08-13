package com.elegion.test.behancer.ui.projects;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.elegion.test.behancer.data_utils.service.ProjectService;
import com.example.domain.model.project.ProjectResponse;

import java.security.Provider;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

public class UserProjectsViewModel extends ProjectsViewModel {

    String mUsername;

    @Inject
    public UserProjectsViewModel(ProjectService service, ProjectsAdapter.OnItemClickListener onItemClickListener, String username) {
        super(service, onItemClickListener);
        mUsername = username;
        updateProjects();
    }

    @Override
    protected void updateProjects() {
        mDisposable = mService.getUserProjects(mUsername)
                .doOnSubscribe(disposable -> mIsLoading.postValue(true))
                .doFinally(() -> mIsLoading.postValue(false))
                .doOnSuccess(response -> mIsErrorVisible.postValue(false))
                .subscribeOn(Schedulers.io())
                .subscribe(
                        response -> {
                            mService.clearProjects();
                            mService.insertProjects(response);
                        },
                        throwable -> {
                            boolean value = mProjects.getValue() == null || mProjects.getValue().size() == 0;
                            mIsErrorVisible.postValue(value);
                        });
    }
}
