package com.elegion.test.behancer.data_utils.repository;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.elegion.test.behancer.data_utils.api.BehanceApi;
import com.example.data.BuildConfig;
import com.example.domain.model.project.Project;
import com.example.domain.model.project.ProjectResponse;
import com.example.domain.model.project.RichProject;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class ProjectServerRepository implements ProjectRepository {

    @Inject
    BehanceApi mApi;

    @Override
    public Single<List<Project>> getProjects() {
        return mApi.getProjects(BuildConfig.API_QUERY).map(ProjectResponse::getProjects);
    }

    @Override
    public LiveData<PagedList<RichProject>> getProjectsPaged() {
        throw new UnsupportedOperationException("Method getProjectsPaged() not yet implemented for server repository!");
    }

    @Override
    public Single<List<Project>> getUserProjects(String username) {
        return mApi.getUserProjects(username).map(ProjectResponse::getProjects);
    }

    @Override
    public void insertProjects(List<Project> projects) {
        // do nothing
    }

    @Override
    public void clearProjects() {
        throw new UnsupportedOperationException();
    }
}
