package com.elegion.test.behancer.data_utils.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.domain.model.project.Cover;
import com.example.domain.model.project.Owner;
import com.example.domain.model.project.Project;
import com.example.domain.model.user.Image;
import com.example.domain.model.user.User;

@Database(entities = {Project.class, Cover.class, Owner.class, User.class, Image.class}, version = 3)
public abstract class BehanceDatabase extends RoomDatabase {

    public abstract BehanceDao getBehanceDao();
}
