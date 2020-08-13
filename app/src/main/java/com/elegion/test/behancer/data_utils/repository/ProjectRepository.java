package com.elegion.test.behancer.data_utils.repository;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.example.domain.model.project.Project;
import com.example.domain.model.project.RichProject;

import java.util.List;

import io.reactivex.Single;

public interface ProjectRepository {

    int PAGE_SIZE = 10;
    String SERVER = "SERVER";
    String DB = "DB";

    Single<List<Project>> getProjects();

    LiveData<PagedList<RichProject>> getProjectsPaged();

    Single<List<Project>> getUserProjects(String username);

    void insertProjects(List<Project> projects);

    void clearProjects();
}
