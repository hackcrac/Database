package com.example.android.database;

import android.os.Handler;
import android.os.Looper;

import com.example.android.database.Database.AppDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {
    private static AppExecutors sInstance;
    private static final Object LOCK = new Object();


    private final Executor diskIO;
    private final Executor mainThread;


    AppExecutors(Executor diskIO, Executor mainthread){
        this.diskIO = diskIO;
        this.mainThread = mainthread;
    }

    public static AppExecutors getsInstance(){
        if(sInstance == null){
            synchronized (LOCK){
                sInstance = new AppExecutors(Executors.newSingleThreadExecutor(),
                        new MainThreadExecutor());
            }
        }
        return sInstance;
    }

    public Executor getDiskIO() {
        return diskIO;
    }

    public Executor getMainThread() {
        return mainThread;
    }

    private static class MainThreadExecutor implements Executor {
        Handler handler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(Runnable command) {
            handler.post(command);
        }
    }
}
