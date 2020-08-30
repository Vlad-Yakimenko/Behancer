package com.elegion.test.behancer.data.repository;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.elegion.domain.model.project.Owner;
import com.elegion.domain.model.project.Project;
import com.elegion.domain.model.project.RichProject;
import com.elegion.domain.repository.ProjectRepository;
import com.elegion.test.behancer.data.database.BehanceDao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class ProjectDBRepository implements ProjectRepository {

    @Inject
    BehanceDao mBehanceDao;

    @Override
    public Single<List<Project>> getProjects() {
        return Single.fromCallable(() -> {
            List<Project> projects = mBehanceDao.getProjects();

            for (Project project : projects) {
                project.setOwners(mBehanceDao.getOwnersFromProject(project.getId()));
            }

            return projects;
        });
    }

    @Override
    public LiveData<PagedList<RichProject>> getProjectsPaged() {
        return new LivePagedListBuilder<>(mBehanceDao.getProjectsPaged(), 10).build();
    }

    @Override
    public Single<List<Project>> getUserProjects(final String username) {
        return null;
    }

    public void insertProjects(List<Project> projects) {
        mBehanceDao.insertProjects(projects);

        List<Owner> owners = getOwners(projects);

        mBehanceDao.clearOwnerTable();
        mBehanceDao.insertOwners(owners);
    }

    @Override
    public void clearProjects() {
        mBehanceDao.clearProjects();
    }

    private List<Owner> getOwners(List<Project> projects) {
        List<Owner> owners = new ArrayList<>();

        for (int i = 0; i < projects.size(); i++) {
            Owner owner = projects.get(i).getOwners().get(0);
            owner.setId(i);
            owner.setProjectId(projects.get(i).getId());
            owners.add(owner);
        }

        return owners;
    }
}
