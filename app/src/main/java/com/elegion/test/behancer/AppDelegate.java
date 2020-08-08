package com.elegion.test.behancer;

import android.app.Application;

import com.elegion.test.behancer.di.AppComponent;
import com.elegion.test.behancer.di.AppModule;
import com.elegion.test.behancer.di.DaggerAppComponent;
import com.elegion.test.behancer.di.NetworkModule;
import com.elegion.test.behancer.di.RepositoryModule;
import com.elegion.test.behancer.di.ServiceModule;

public class AppDelegate extends Application {

    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .repositoryModule(new RepositoryModule())
                .serviceModule(new ServiceModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}
