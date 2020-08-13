package com.elegion.test.behancer.data_utils.service;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.example.domain.model.project.Project;
import com.example.domain.model.project.RichProject;

import java.util.List;

import io.reactivex.Single;

public interface ProjectService {

    Single<List<Project>> getProjects();

    LiveData<PagedList<RichProject>> getProjectsPaged();

    Single<List<Project>> getUserProjects(String username);

    void insertProjects(List<Project> projects);

    void clearProjects();
}
