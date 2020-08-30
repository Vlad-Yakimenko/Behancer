package com.elegion.domain.service;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.elegion.domain.model.project.Project;
import com.elegion.domain.model.project.RichProject;

import java.util.List;

import io.reactivex.Single;

public interface ProjectService {

    Single<List<Project>> getProjects();

    LiveData<PagedList<RichProject>> getProjectsPaged();

    Single<List<Project>> getUserProjects(String username);

    void insertProjects(List<Project> projects);

    void clearProjects();
}
