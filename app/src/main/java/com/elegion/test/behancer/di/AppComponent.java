package com.elegion.test.behancer.di;

import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.ui.projects.AllProjectsFragment;
import com.elegion.test.behancer.ui.projects.UserProjectsFragment;
import com.elegion.test.behancer.ui.projects.UserProjectsPresenter;
import com.example.domain.service.ProfileService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, RepositoryModule.class, ServiceModule.class})
public interface AppComponent {

    void inject(AllProjectsFragment injector);
    void inject(UserProjectsFragment injector);
    void inject(UserProjectsPresenter injector);

    BehanceApi api();
    ProfileService profileService();
}
