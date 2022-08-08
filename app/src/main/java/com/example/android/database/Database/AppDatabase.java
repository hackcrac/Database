package com.example.android.database.Database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PersonEntry.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "PersonList";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context){
        if(sInstance==null){
            synchronized (LOCK){
                Log.d(LOG_TAG,"Creating Database");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,
                        DATABASE_NAME).build();
            }
        }
        Log.d(LOG_TAG,"Getting the database instance");
        return sInstance;
    }
    public abstract PersonDao personDao();
}
