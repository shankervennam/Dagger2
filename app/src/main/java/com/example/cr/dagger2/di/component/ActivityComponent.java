package com.example.cr.dagger2.di.component;

import com.example.cr.dagger2.MainActivity;
import com.example.cr.dagger2.di.PerActivity;

import dagger.Component;


@PerActivity
@Component()
public interface ActivityComponent
{
    void inject(MainActivity mainActivity);
}
