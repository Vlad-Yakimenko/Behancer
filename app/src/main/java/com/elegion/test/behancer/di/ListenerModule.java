package com.elegion.test.behancer.di;

import com.elegion.test.behancer.ui.projects.ProjectsAdapter;

import toothpick.config.Module;

public class ListenerModule extends Module {

    public ListenerModule(ProjectsAdapter.OnItemClickListener onItemClickListener) {
        bind(ProjectsAdapter.OnItemClickListener.class).toInstance(onItemClickListener);
    }
}
