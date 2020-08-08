package com.elegion.test.behancer.ui.projects;

import android.os.Bundle;

import com.elegion.test.behancer.AppDelegate;
import com.elegion.test.behancer.common.BasePresenter;

public class UserProjectsFragment extends ProjectsFragment {

    UserProjectsPresenter mPresenter;

    public static UserProjectsFragment newInstance() {
        return new UserProjectsFragment();
    }

    public void setPresenter(UserProjectsPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    protected BasePresenter<ProjectsView> getPresenter() {
        return mPresenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppDelegate.getAppComponent().inject(this);
    }

    @Override
    public void onItemClick(String username) {
        // do nothing
    }


}
