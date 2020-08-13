package com.elegion.test.behancer.di;

import com.elegion.test.behancer.ui.projects.ProjectsViewModel;
import com.elegion.test.behancer.ui.projects.UserProjectsViewModel;

import toothpick.config.Module;

public class UserProjectsFragmentModule extends Module {

    public UserProjectsFragmentModule(String username) {
        bind(ProjectsViewModel.class).to(UserProjectsViewModel.class);
        bind(String.class).toInstance(username);
    }
}
