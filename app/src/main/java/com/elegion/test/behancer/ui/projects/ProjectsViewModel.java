package com.elegion.test.behancer.ui.projects;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;
import android.support.v4.widget.SwipeRefreshLayout;

import com.elegion.test.behancer.data_utils.service.ProjectService;
import com.example.domain.model.project.RichProject;

import io.reactivex.disposables.Disposable;

public abstract class ProjectsViewModel extends ViewModel {

    protected Disposable mDisposable;

    protected ProjectService mService;

    private ProjectsAdapter.OnItemClickListener mOnItemClickListener;
    protected MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    protected MutableLiveData<Boolean> mIsErrorVisible = new MutableLiveData<>();
    protected LiveData<PagedList<RichProject>> mProjects;
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = this::updateProjects;

    public ProjectsViewModel(ProjectService service, ProjectsAdapter.OnItemClickListener itemClickListener) {
        mService = service;
        mOnItemClickListener = itemClickListener;
        this.mProjects = mService.getProjectsPaged();
    }

    protected abstract void updateProjects();

    @Override
    public void onCleared() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    public ProjectsAdapter.OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return mIsLoading;
    }

    public MutableLiveData<Boolean> getIsErrorVisible() {
        return mIsErrorVisible;
    }

    public LiveData<PagedList<RichProject>> getProjects() {
        return mProjects;
    }

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return mOnRefreshListener;
    }
}
