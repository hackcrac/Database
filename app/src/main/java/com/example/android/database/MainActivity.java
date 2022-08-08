package com.example.android.database;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.database.Database.AppDatabase;
import com.example.android.database.Database.PersonEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.Duration;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "retrieving data" ;
    MyAdapter adapter;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton addPerson = findViewById(R.id.floatingActionButton2);
        addPerson.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,AddNameActivity.class);
            startActivity(intent);
        });
        db = AppDatabase.getInstance(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new MyAdapter();
        retrievePerson();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


    private void retrievePerson(){

        AppViewModel viewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        viewModel.getList().observe(this, new Observer<List<PersonEntry>>() {
            @Override
            public void onChanged(List<PersonEntry> personEntries) {
                Log.e(TAG, "retrievePerson: Dtat ");
                adapter.setPerson(personEntries);
            }
        });
    }
}