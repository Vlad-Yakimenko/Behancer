package com.elegion.test.behancer.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.elegion.domain.model.project.Cover;
import com.elegion.domain.model.project.Owner;
import com.elegion.domain.model.project.Project;
import com.elegion.domain.model.user.Image;
import com.elegion.domain.model.user.User;

@Database(entities = {Project.class, Cover.class, Owner.class, User.class, Image.class}, version = 3)
public abstract class BehanceDatabase extends RoomDatabase {

    public abstract BehanceDao getBehanceDao();
}
