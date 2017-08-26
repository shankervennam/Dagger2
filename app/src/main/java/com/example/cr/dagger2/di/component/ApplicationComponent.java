package com.example.cr.dagger2.di.component;


import android.app.Application;
import android.content.Context;

import com.example.cr.dagger2.DemoApplication;
import com.example.cr.dagger2.data.DataHelper;
import com.example.cr.dagger2.data.DataManager;
import com.example.cr.dagger2.data.SharedPrefClass;
import com.example.cr.dagger2.di.ApplicationContext;
import com.example.cr.dagger2.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent
{
    void inject(DemoApplication demoApplication);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    DataManager getDataMAnager();

    SharedPrefClass getSharedPref();

    DataHelper dataHelper();
}
