package varmtech.com.weatherapp;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ProcessLifecycleOwner;
import android.arch.persistence.room.Room;
import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import varmtech.com.weatherapp.constants.AppConstants;
import varmtech.com.weatherapp.databases.database.AppDatabase;
import varmtech.com.weatherapp.webservice.ApiService;

public class AppApplication extends Application implements LifecycleObserver {
    private static AppApplication appApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        appApplication = this;
        setLifecycleObserver();
    }

    // Context
    public static AppApplication getInstance() {
        return appApplication;
    }

    // Retrofit
    private Retrofit initRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ApiService getNetworkService() {
        return initRetrofit().create(ApiService.class);
    }

    // Database
    private AppDatabase initDatabase() {
        return Room.databaseBuilder(this, AppDatabase.class, AppConstants.DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    public AppDatabase getDatabase() {
        return initDatabase();
    }

    // Lifecycle
    private void setLifecycleObserver() {
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onMoveToForeground() {
        Log.i("myTag", "App is in foreground");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onMoveToBackground() {
        Log.i("myTag", "App is in background");
    }
}
