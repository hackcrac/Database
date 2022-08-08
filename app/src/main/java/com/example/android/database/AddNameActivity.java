package com.example.android.database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.database.Database.AppDatabase;
import com.example.android.database.Database.PersonEntry;

public class AddNameActivity extends AppCompatActivity {

    private AppDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_name);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
        }
        database = AppDatabase.getInstance(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.addmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addMenu) {
            onClickSaveButton();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickSaveButton(){
        EditText editText = findViewById(R.id.editTextTextPersonName);
        String name = String.valueOf(editText.getText()).trim();
        if(!TextUtils.isEmpty(name)){
            final PersonEntry personEntry = new PersonEntry(name);
            AppExecutors.getsInstance().getDiskIO().execute(new Runnable() {
                @Override
                public void run() {
                    database.personDao().InsertPerson(personEntry);
                }
            });
            finish();
        }
        else{
            Toast.makeText(this,"Please enter a name",Toast.LENGTH_SHORT).show();
        }

    }
}