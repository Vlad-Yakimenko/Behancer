package com.example.domain.service;

import com.example.domain.ApiUtils;
import com.example.domain.model.project.Project;
import com.example.domain.repository.ProjectRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class ProjectServiceImpl implements ProjectService {

    @Inject
    @Named(ProjectRepository.SERVER)
    ProjectRepository mServerRepository;

    @Inject
    @Named(ProjectRepository.DB)
    ProjectRepository mDBRepository;

    @Inject
    public ProjectServiceImpl() {
    }

    @Override
    public Single<List<Project>> getProjects() {
        return mServerRepository.getProjects()
                .subscribeOn(Schedulers.io())
                .doOnSuccess(mDBRepository::insertProjects)
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass())
                                ? mDBRepository.getProjects().blockingGet()
                                : null);
    }

    @Override
    public Single<List<Project>> getUserProjects(String username) {
        return mServerRepository.getUserProjects(username);
    }

    @Override
    public void insertProjects(List<Project> projects) {
        mDBRepository.insertProjects(projects);
    }
}
