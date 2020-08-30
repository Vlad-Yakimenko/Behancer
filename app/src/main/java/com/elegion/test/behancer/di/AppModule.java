package com.elegion.test.behancer.di;

import androidx.room.Room;

import com.elegion.test.behancer.AppDelegate;
import com.elegion.test.behancer.data.database.BehanceDao;
import com.elegion.test.behancer.data.database.BehanceDatabase;

import toothpick.config.Module;

public class AppModule extends Module {

    private final AppDelegate mApp;
    private final BehanceDatabase mDatabase;
    private final BehanceDao mDao;

    public AppModule(AppDelegate app) {
        this.mApp = app;
        mDatabase = provideDatabase();
        mDao = provideBehanceDao();

        bind(AppDelegate.class).toInstance(mApp);
        bind(BehanceDatabase.class).toInstance(mDatabase);
        bind(BehanceDao.class).toInstance(mDao);
    }

    AppDelegate provideApp() {
        return mApp;
    }

    BehanceDao provideBehanceDao() {
        return mDatabase.getBehanceDao();
    }

    BehanceDatabase provideDatabase() {
        return Room.databaseBuilder(mApp, BehanceDatabase.class, "behance_database")
                .fallbackToDestructiveMigration()
                .build();
    }
}
