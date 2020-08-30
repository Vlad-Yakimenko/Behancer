package com.elegion.test.behancer.data.database;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.elegion.domain.model.project.Cover;
import com.elegion.domain.model.project.Owner;
import com.elegion.domain.model.project.Project;
import com.elegion.domain.model.project.RichProject;
import com.elegion.domain.model.user.Image;
import com.elegion.domain.model.user.User;

import java.util.List;

@Dao
public interface BehanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProjects(List<Project> projects);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCovers(List<Cover> covers);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOwners(List<Owner> owners);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertImage(Image image);

    @Query("select * from project")
    List<Project> getProjects();

    @Query("select * from project ORDER BY published_on DESC")
    LiveData<List<RichProject>> getProjectsLive();

    @Query("select * from project ORDER BY published_on DESC")
    DataSource.Factory<Integer, RichProject> getProjectsPaged();

    @Query("select * from owner where project_id = :projectId")
    List<Owner> getOwnersFromProject(int projectId);

    @Query("select * from user where username = :userName")
    User getUserByName(String userName);

    @Query("select * from user where username = :userName")
    LiveData<User> getUserByNameLive(String userName);

    @Query("select photo_id, photo_url from user where id = :userId")
    Image getImageFromUser(int userId);

    @Query("delete from owner")
    void clearOwnerTable();

    @Query("delete from cover")
    void clearCoverTable();

    @Query("delete from image")
    void clearImageTable();

    @Query("select * from user")
    List<User> getUsers();

    @Query("select * from image")
    List<Image> getImages();

    @Query("DELETE FROM project")
    void clearProjects();
}
