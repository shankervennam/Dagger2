package com.example.cr.dagger2.di.module;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.cr.dagger2.di.ActivityContext;
import com.example.cr.dagger2.di.ApplicationContext;
import com.example.cr.dagger2.di.DataBaseInfo;

import dagger.Provides;

public class ApplicationModule
{
    private final Application application;

    public ApplicationModule(Application application)
    {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext()
    {
        return application;
    }

    @Provides
    Application provideApplication()
    {
        return application;
    }

    @Provides
    @DataBaseInfo
    String provideDatabaseName()
    {
        return "demo-dagger.db";
    }

    @Provides
    @DataBaseInfo
    Integer provideDataBaseVersion()
    {
        return 2;
    }

    @Provides
    SharedPreferences provideSharedPrefs()
    {
        return application.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE);
    }
}
