package com.elegion.test.behancer.ui.projects;

import android.os.Bundle;

import com.elegion.test.behancer.AppDelegate;
import com.elegion.test.behancer.common.BasePresenter;

import javax.inject.Inject;

public class AllProjectsFragment extends ProjectsFragment {

    @Inject
    ProjectsPresenter mPresenter;

    public static AllProjectsFragment newInstance() {
        return new AllProjectsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppDelegate.getAppComponent().inject(this);
    }

    @Override
    public void onItemClick(String username) {
        mPresenter.openProfileFragment(username);
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }
}
