package com.elegion.test.behancer.ui.projects;

import androidx.recyclerview.widget.RecyclerView;

import com.elegion.test.behancer.databinding.ProjectBinding;
import com.example.domain.model.project.RichProject;

public class ProjectsHolder extends RecyclerView.ViewHolder {

    private ProjectBinding mProjectBinding;

    public ProjectsHolder(ProjectBinding binding) {
        super(binding.getRoot());
        mProjectBinding = binding;
    }

    public void bind(RichProject item, ProjectsAdapter.OnItemClickListener onItemClickListener) {
        mProjectBinding.setProject(new ProjectListItemViewModel(item));
        mProjectBinding.setOnItemClickListener(onItemClickListener);
        mProjectBinding.executePendingBindings();
    }
}
