package com.elegion.test.behancer.di;

import com.elegion.test.behancer.data.repository.ProfileDBRepository;
import com.elegion.test.behancer.data.repository.ProfileServerRepository;
import com.elegion.test.behancer.data.repository.ProjectDBRepository;
import com.elegion.test.behancer.data.repository.ProjectServerRepository;
import com.example.domain.repository.ProfileRepository;
import com.example.domain.repository.ProjectRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    @Named(ProjectRepository.SERVER)
    ProjectRepository provideProjectServerRepository(ProjectServerRepository projectServerRepository) {
        return projectServerRepository;
    }

    @Provides
    @Singleton
    @Named(ProjectRepository.DB)
    ProjectRepository provideProjectDBRepository(ProjectDBRepository projectDBRepository) {
        return projectDBRepository;
    }

    @Provides
    @Singleton
    @Named(ProfileRepository.SERVER)
    ProfileRepository provideProfileServerRepository(ProfileServerRepository profileServerRepository) {
        return profileServerRepository;
    }

    @Provides
    @Singleton
    @Named(ProfileRepository.DB)
    ProfileRepository provideProfileDBRepository(ProfileDBRepository profileDBRepository) {
        return profileDBRepository;
    }

}
