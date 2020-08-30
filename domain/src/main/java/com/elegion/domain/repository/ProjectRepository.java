package com.elegion.domain.repository;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.elegion.domain.model.project.Project;
import com.elegion.domain.model.project.RichProject;

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
