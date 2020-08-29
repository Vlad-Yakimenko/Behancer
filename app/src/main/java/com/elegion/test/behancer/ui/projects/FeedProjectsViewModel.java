package com.elegion.test.behancer.ui.projects;

import com.elegion.test.behancer.data_utils.service.ProjectService;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

public class FeedProjectsViewModel extends ProjectsViewModel {

    @Inject
    public FeedProjectsViewModel(ProjectService service, ProjectsAdapter.OnItemClickListener onItemClickListener) {
        super(service, onItemClickListener);
        updateProjects();
    }

    @Override
    protected void updateProjects() {
        mDisposable = mService.getProjects()
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
