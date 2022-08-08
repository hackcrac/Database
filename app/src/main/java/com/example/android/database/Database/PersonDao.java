package com.example.android.database.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM people")
    LiveData<List<PersonEntry>> loadAllTasks();

    @Insert
    void InsertPerson(PersonEntry personEntry);

    @Delete
    void deletePerson(PersonEntry personEntry);
}
