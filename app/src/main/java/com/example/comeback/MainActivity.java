package com.example.comeback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    Adapter adapter;
    List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        NoteDatabase db = new NoteDatabase(this);
        notes = db.getNotes();

        recyclerView = findViewById(R.id.listOfNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this,db.getNotes());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add){
            Intent intent = new Intent(this,AddNote.class);
            startActivity(intent);
            Toast.makeText(this,"ADD button is clicked",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}