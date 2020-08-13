package com.elegion.test.behancer.ui.projects;

import android.content.Context;

import com.elegion.test.behancer.AppDelegate;
import com.elegion.test.behancer.di.ListenerModule;
import com.elegion.test.behancer.di.UserProjectsFragmentModule;

import toothpick.Scope;
import toothpick.Toothpick;

import static com.elegion.test.behancer.ui.profile.ProfileActivity.USERNAME_KEY;

public class UserProjectsFragment extends ProjectsFragment {

    {
        mOnItemClickListener = username -> {
            // do nothing
        };
    }

    public static UserProjectsFragment newInstance() {
        return new UserProjectsFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Scope scope = Toothpick.openScopes(AppDelegate.class, UserProjectsFragment.class);
        assert getArguments() != null;
        scope.installModules(new ListenerModule(mOnItemClickListener), new UserProjectsFragmentModule(getArguments().getString(USERNAME_KEY)));
        Toothpick.inject(this, scope);
    }

    @Override
    public void onDetach() {
        Toothpick.closeScope(UserProjectsFragment.class);
        super.onDetach();
    }
}
