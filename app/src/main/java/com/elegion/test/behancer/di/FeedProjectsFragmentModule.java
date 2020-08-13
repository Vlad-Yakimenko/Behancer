package com.elegion.test.behancer.di;

import com.elegion.test.behancer.ui.projects.FeedProjectsViewModel;
import com.elegion.test.behancer.ui.projects.ProjectsAdapter;
import com.elegion.test.behancer.ui.projects.ProjectsViewModel;

import toothpick.config.Module;

public class FeedProjectsFragmentModule extends Module {

    public FeedProjectsFragmentModule() {
        bind(ProjectsViewModel.class).to(FeedProjectsViewModel.class).singletonInScope();
    }
}
