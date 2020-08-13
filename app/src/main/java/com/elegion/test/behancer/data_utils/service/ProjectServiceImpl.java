package com.elegion.test.behancer.data_utils.service;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.elegion.test.behancer.data_utils.repository.ProjectRepository;
import com.example.domain.model.project.Project;
import com.example.domain.model.project.RichProject;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;

public class ProjectServiceImpl implements ProjectService {

    @Inject
    @Named(ProjectRepository.SERVER)
    ProjectRepository mServerRepository;

    @Inject
    @Named(ProjectRepository.DB)
    ProjectRepository mDBRepository;

    @Override
    public Single<List<Project>> getProjects() {
        return mServerRepository.getProjects();
    }

    @Override
    public LiveData<PagedList<RichProject>> getProjectsPaged() {
        return mDBRepository.getProjectsPaged();
    }

    @Override
    public Single<List<Project>> getUserProjects(String username) {
        return mServerRepository.getUserProjects(username);
    }

    @Override
    public void insertProjects(List<Project> projects) {
        mDBRepository.insertProjects(projects);
    }

    @Override
    public void clearProjects() {
        mDBRepository.clearProjects();
    }
}
