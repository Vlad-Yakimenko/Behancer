package com.elegion.test.behancer.di;

import com.example.domain.service.ProfileService;
import com.example.domain.service.ProfileServiceImpl;
import com.example.domain.service.ProjectService;
import com.example.domain.service.ProjectServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {

    @Provides
    @Singleton
    ProjectService provideProjectService(ProjectServiceImpl projectService) {
        return projectService;
    }

    @Provides
    @Singleton
    ProfileService provideProfileService(ProfileServiceImpl profileService) {
        return profileService;
    }
}
