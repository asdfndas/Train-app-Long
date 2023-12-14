package com.example.trainticketproject.models;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;

import java.util.Locale;

public class MyApplication extends Application {

    private static MyApplication instance;
    private String currentLanguageCode;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        setLocale(getLanguageFromPreferences());
    }

    public static synchronized MyApplication getInstance() {
        return instance;
    }

    public void setLocale(String languageCode) {
        currentLanguageCode = languageCode;

        // Lưu ngôn ngữ được chọn vào SharedPreferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putString("language", languageCode).apply();

        // Áp dụng ngôn ngữ cho tất cả các Activity
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
    }

    public String getCurrentLanguageCode() {
        return currentLanguageCode;
    }

    private String getLanguageFromPreferences() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getString("language", "en"); // Mặc định là tiếng Anh
    }
}

