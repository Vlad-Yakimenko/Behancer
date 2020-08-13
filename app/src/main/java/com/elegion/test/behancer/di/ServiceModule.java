package com.elegion.test.behancer.di;

import com.elegion.test.behancer.data_utils.service.ProfileService;
import com.elegion.test.behancer.data_utils.service.ProfileServiceImpl;
import com.elegion.test.behancer.data_utils.service.ProjectService;
import com.elegion.test.behancer.data_utils.service.ProjectServiceImpl;

import toothpick.config.Module;

public class ServiceModule extends Module {

    public ServiceModule() {
        bind(ProjectService.class).to(ProjectServiceImpl.class);
        bind(ProfileService.class).to(ProfileServiceImpl.class);
    }
}
