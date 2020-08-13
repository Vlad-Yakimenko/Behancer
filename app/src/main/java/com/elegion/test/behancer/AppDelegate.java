package com.elegion.test.behancer;

import android.app.Application;
import android.app.Service;

import com.elegion.test.behancer.app.FactoryRegistry;
import com.elegion.test.behancer.app.MemberInjectorRegistry;
import com.elegion.test.behancer.di.AppModule;
import com.elegion.test.behancer.di.NetworkModule;
import com.elegion.test.behancer.di.RepositoryModule;
import com.elegion.test.behancer.di.ServiceModule;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.configuration.Configuration;
import toothpick.registries.FactoryRegistryLocator;
import toothpick.registries.MemberInjectorRegistryLocator;
import toothpick.smoothie.module.SmoothieApplicationModule;

public class AppDelegate extends Application {

    private static Scope sAppScope;

    @Override
    public void onCreate() {
        super.onCreate();

        Toothpick.setConfiguration(Configuration.forProduction().disableReflection());
        FactoryRegistryLocator.setRootRegistry(new FactoryRegistry());
        MemberInjectorRegistryLocator.setRootRegistry(new MemberInjectorRegistry());

        sAppScope = Toothpick.openScope(AppDelegate.class);
        sAppScope.installModules(new SmoothieApplicationModule(this), new AppModule(this), new NetworkModule(), new RepositoryModule(), new ServiceModule());
    }

    public static Scope getAppScope() {
        return sAppScope;
    }
}
