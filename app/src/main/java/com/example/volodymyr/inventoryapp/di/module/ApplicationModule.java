package com.example.volodymyr.inventoryapp.di.module;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ApplicationModule {
    private static final String DB_NAME = "product.db";
    private static final int DB_VERSION = 1;

    @Binds
    abstract Context bindContext(Application application);

    @Provides
    @DatabaseInfo
    static String provideDatabaseName() {
        return DB_NAME;
    }

    @Provides
    @DatabaseInfo
    static Integer provideDatabaseVersion() {
        return DB_VERSION;
    }
}
