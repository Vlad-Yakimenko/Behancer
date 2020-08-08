package com.example.domain.service;

import com.example.domain.model.project.Project;

import java.util.List;

import io.reactivex.Single;

public interface ProjectService {

    Single<List<Project>> getProjects();

    Single<List<Project>> getUserProjects(String username);

    void insertProjects(List<Project> projects);
}
