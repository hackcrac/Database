package com.example.android.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.database.Database.AppDatabase;
import com.example.android.database.Database.PersonEntry;

import java.util.List;

public class AppViewModel extends AndroidViewModel {
    private final AppDatabase db;
    private  LiveData<List<PersonEntry>> list;
    public AppViewModel(@NonNull Application application) {
        super(application);
        db = AppDatabase.getInstance(application);
        list = db.personDao().loadAllTasks();
    }

    public LiveData<List<PersonEntry>> getList(){
        return list;
    }
}
