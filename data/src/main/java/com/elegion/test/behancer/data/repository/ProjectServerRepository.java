package com.elegion.test.behancer.data.repository;

import com.elegion.test.behancer.data.api.BehanceApi;
import com.example.data.BuildConfig;
import com.example.domain.model.project.Project;
import com.example.domain.model.project.ProjectResponse;
import com.example.domain.repository.ProjectRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class ProjectServerRepository implements ProjectRepository {

    @Inject
    BehanceApi mApi;

    @Inject
    public ProjectServerRepository() {
    }

    @Override
    public Single<List<Project>> getProjects() {
        return mApi.getProjects(BuildConfig.API_QUERY).map(new Function<ProjectResponse, List<Project>>() {
            @Override
            public List<Project> apply(ProjectResponse projectResponse) throws Exception {
                return projectResponse.getProjects();
            }
        });
    }

    @Override
    public Single<List<Project>> getUserProjects(String username) {
        return mApi.getUserProjects(username).map(new Function<ProjectResponse, List<Project>>() {
            @Override
            public List<Project> apply(ProjectResponse projectResponse) throws Exception {
                return projectResponse.getProjects();
            }
        });
    }

    @Override
    public void insertProjects(List<Project> projects) {
        // do nothing
    }
}
