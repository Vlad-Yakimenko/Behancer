package com.elegion.test.behancer.di;

import com.elegion.test.behancer.data_utils.repository.ProfileDBRepository;
import com.elegion.test.behancer.data_utils.repository.ProfileServerRepository;
import com.elegion.test.behancer.data_utils.repository.ProjectDBRepository;
import com.elegion.test.behancer.data_utils.repository.ProjectServerRepository;
import com.elegion.test.behancer.data_utils.repository.ProfileRepository;
import com.elegion.test.behancer.data_utils.repository.ProjectRepository;

import toothpick.config.Module;

public class RepositoryModule extends Module {

    public RepositoryModule() {
        bind(ProjectRepository.class).withName(ProjectRepository.SERVER).to(ProjectServerRepository.class);
        bind(ProjectRepository.class).withName(ProjectRepository.DB).to(ProjectDBRepository.class);
        bind(ProfileRepository.class).withName(ProfileRepository.SERVER).to(ProfileServerRepository.class);
        bind(ProfileRepository.class).withName(ProfileRepository.DB).to(ProfileDBRepository.class);
    }
}
