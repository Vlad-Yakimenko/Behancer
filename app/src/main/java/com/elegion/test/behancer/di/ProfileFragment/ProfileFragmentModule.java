package com.elegion.test.behancer.di.ProfileFragment;

import com.elegion.test.behancer.ui.profile.ProfileView;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileFragmentModule {

    private ProfileView mFragment;

    public ProfileFragmentModule(ProfileView fragment) {
        mFragment = fragment;
    }

    @ProfileFragmentScope
    @Provides
    public ProfileView provideProfileView() {
        return mFragment;
    }
}
