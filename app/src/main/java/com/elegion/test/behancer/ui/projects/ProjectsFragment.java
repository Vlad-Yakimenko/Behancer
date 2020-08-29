package com.elegion.test.behancer.ui.projects;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elegion.test.behancer.databinding.ProjectsBinding;

import javax.inject.Inject;

public abstract class ProjectsFragment extends Fragment {

    @Inject
    ProjectsViewModel mProjectsViewModel;
    protected ProjectsAdapter.OnItemClickListener mOnItemClickListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ProjectsBinding binding = ProjectsBinding.inflate(inflater, container, false);
        binding.setVm(mProjectsViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }
}
