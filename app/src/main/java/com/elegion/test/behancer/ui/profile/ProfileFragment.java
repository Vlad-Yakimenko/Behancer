package com.elegion.test.behancer.ui.profile;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elegion.test.behancer.AppDelegate;
import com.elegion.test.behancer.common.SingleFragmentActivity;
import com.elegion.test.behancer.databinding.ProfileBinding;
import com.elegion.test.behancer.di.ProfileFragment.ProfileFragmentModule;
import com.elegion.test.behancer.ui.projects.ProjectsFragment;
import com.elegion.test.behancer.ui.projects.UserProjectsFragment;

import java.util.Objects;

import javax.inject.Inject;

import toothpick.Scope;
import toothpick.Toothpick;

import static com.elegion.test.behancer.ui.profile.ProfileActivity.USERNAME_KEY;

public class ProfileFragment extends Fragment {

    public static final String PROFILE_KEY = "PROFILE_KEY";

    @Inject
    ProfileViewModel mProfileViewModel;

    public static ProfileFragment newInstance(Bundle args) {
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Scope scope = Toothpick.openScopes(AppDelegate.class, ProfileFragment.class);
        scope.installModules(new ProfileFragmentModule());
        Toothpick.inject(this, scope);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ProfileBinding binding = ProfileBinding.inflate(inflater, container, false);
        binding.setVm(mProfileViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            String username = getArguments().getString(PROFILE_KEY);
            mProfileViewModel.setUsername(username);
            mProfileViewModel.setOnClickListener(view -> {
                ProjectsFragment fragment = UserProjectsFragment.newInstance();
                Bundle args = new Bundle();
                args.putString(USERNAME_KEY, mProfileViewModel.getUsername());
                fragment.setArguments(args);
                ((SingleFragmentActivity) Objects.requireNonNull(getActivity())).changeFragment(fragment);
            });

            if (getActivity() != null) {
                getActivity().setTitle(username);
            }
        }

        mProfileViewModel.obtainProfile();
    }

    @Override
    public void onDetach() {
        mProfileViewModel.dispatchDetach();
        Toothpick.closeScope(ProfileFragment.class);
        super.onDetach();
    }
}
