package com.elegion.test.behancer.ui.projects;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import com.elegion.test.behancer.AppDelegate;
import com.elegion.test.behancer.di.FeedProjectsFragmentModule;
import com.elegion.test.behancer.di.ListenerModule;
import com.elegion.test.behancer.ui.profile.ProfileActivity;
import com.elegion.test.behancer.ui.profile.ProfileFragment;

import toothpick.Scope;
import toothpick.Toothpick;

public class FeedProjectsFragment extends ProjectsFragment {

    {
        mOnItemClickListener = username -> {
            Intent intent = new Intent(getActivity(), ProfileActivity.class);
            Bundle args = new Bundle();
            args.putString(ProfileFragment.PROFILE_KEY, username);
            intent.putExtra(ProfileActivity.USERNAME_KEY, args);
            startActivity(intent);
        };
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Scope scope = Toothpick.openScopes(AppDelegate.class, FeedProjectsFragment.class);
        scope.installModules(new ListenerModule(mOnItemClickListener), new FeedProjectsFragmentModule());
        Toothpick.inject(this, scope);
    }

    public static Fragment newInstance() {
        return new FeedProjectsFragment();
    }

    @Override
    public void onDetach() {
        Toothpick.closeScope(FeedProjectsFragment.class);
        super.onDetach();
    }
}
