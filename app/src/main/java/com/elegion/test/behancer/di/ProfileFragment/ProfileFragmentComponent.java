package com.elegion.test.behancer.di.ProfileFragment;

import com.elegion.test.behancer.di.AppComponent;
import com.elegion.test.behancer.ui.profile.ProfileFragment;
import com.elegion.test.behancer.ui.profile.ProfilePresenter;
import com.elegion.test.behancer.ui.projects.ProjectsPresenter;

import dagger.Component;

@ProfileFragmentScope
@Component(modules = ProfileFragmentModule.class, dependencies = AppComponent.class)
public interface ProfileFragmentComponent {

    void inject(ProfilePresenter injector);
}
