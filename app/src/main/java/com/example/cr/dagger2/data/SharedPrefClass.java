package com.example.cr.dagger2.data;

import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SharedPrefClass
{
    public static String PREF_KEY_ACCESS_TOKEN = "access_token";

    private SharedPreferences sharedPreferences;

    @Inject
    public SharedPrefClass(SharedPreferences sharedPreferences)
    {
        this.sharedPreferences = sharedPreferences;
    }

    public void put(String key, String value)
    {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public void put(String key, int value)
    {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public void put(String key, float value)
    {
        sharedPreferences.edit().putFloat(key, value).apply();
    }

    public void put(String key, boolean value)
    {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public String get(String key, String defaultValues)
    {
        return sharedPreferences.getString(key, defaultValues);
    }

    public Float get(String key, float defaultValues)
    {
        return sharedPreferences.getFloat(key, defaultValues);
    }

    public Boolean get(String key, Boolean defaultValues)
    {
        return sharedPreferences.getBoolean(key, defaultValues);
    }

    public Integer get(String key, int defaultValues)
    {
        return sharedPreferences.getInt(key, defaultValues);
    }

    public void deleteSavedData(String key)
    {
        sharedPreferences.edit().remove(key).apply();
    }
}
