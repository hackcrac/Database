package com.example.android.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.database.Database.PersonEntry;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private static final String TAG ="MyAdapter";
    List<PersonEntry> personEntryList;
    TextView textView1;
    TextView textView2;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.view_item,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: ");
        PersonEntry personEntry = personEntryList.get(position);
        textView2.setText(personEntry.getName());
        textView1.setText(String.valueOf(personEntry.getId()));
    }

    @Override
    public int getItemCount() {
        if(personEntryList==null){
            return 0;
        }
        return personEntryList.size();
    }


    @SuppressLint("NotifyDataSetChanged")
    public void setPerson(List<PersonEntry> personEntries){
            personEntryList = personEntries;
            notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView2 = itemView.findViewById(R.id.item_textview2);
            textView1 = itemView.findViewById(R.id.item_textview1);
        }
    }
}
