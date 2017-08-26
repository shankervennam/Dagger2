package com.example.cr.dagger2;

import android.app.Application;
import android.content.Context;

import com.example.cr.dagger2.data.DataManager;
import com.example.cr.dagger2.di.component.ApplicationComponent;
import com.example.cr.dagger2.di.module.ApplicationModule;

import javax.inject.Inject;

import dagger.internal.DaggerCollections;

public class DemoApplication extends Application
{
    protected ApplicationComponent applicationComponent;

    @Inject
    DataManager dataManager;

    public static DemoApplication get(Context context)
    {
        return (DemoApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        applicationComponent =
    }
}
