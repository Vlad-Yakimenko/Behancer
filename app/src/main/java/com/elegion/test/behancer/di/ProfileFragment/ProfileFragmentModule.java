package com.elegion.test.behancer.di.ProfileFragment;

import com.elegion.test.behancer.ui.profile.ProfileViewModel;

import toothpick.config.Module;

public class ProfileFragmentModule extends Module {

    public ProfileFragmentModule() {
        bind(ProfileViewModel.class).to(ProfileViewModel.class);
    }
}
