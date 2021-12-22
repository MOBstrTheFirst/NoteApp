package com.example.comeback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Details extends AppCompatActivity {

    Toolbar toolbar;
    FloatingActionButton floatingActionButton;
    TextView detailsOfNote;
    NoteDatabase db;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        db = new NoteDatabase(this);
        note = db.getNote(getIntent().getLongExtra("ID",0));

        getSupportActionBar().setTitle(note.getTitle());

        floatingActionButton = findViewById(R.id.floatingActionButton);
        detailsOfNote = findViewById(R.id.detailsOfNote);

        detailsOfNote.setText(note.getContent());

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteNote(note.getId());
                goToMain();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.editNote){
            Intent intent = new Intent(Details.this,Edit.class);
            intent.putExtra("ID",note.getId());
            startActivity(intent);
            Toast.makeText(this,"editNote is clicked",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToMain() {
        startActivity(new Intent(this,MainActivity.class));
    }
}