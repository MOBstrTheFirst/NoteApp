package com.example.comeback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class Edit extends AppCompatActivity {

    Toolbar toolbar;
    EditText noteTitle, noteDetails;
    String todaysDate;
    String currentTime;
    Calendar calendar;
    NoteDatabase db;
    Note note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        db = new NoteDatabase(this);
        note = db.getNote(getIntent().getLongExtra("ID",0));

        Intent intent = new Intent(Edit.this,Details.class);
        intent.putExtra("ID",note.getId());

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(note.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        noteTitle = findViewById(R.id.noteTitle);
        noteDetails = findViewById(R.id.noteDetails);

        noteTitle.setText(note.getTitle());
        noteDetails.setText(note.getContent());

        noteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                getSupportActionBar().setTitle(note.getTitle());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0){
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        calendar = Calendar.getInstance();
        todaysDate = calendar.get(Calendar.DAY_OF_MONTH) +"."+ (calendar.get(Calendar.MONTH)+1) + "."+ calendar.get(Calendar.YEAR);
        currentTime = pad(calendar.get(Calendar.HOUR)) + ":" + pad(calendar.get(Calendar.MINUTE));

        Log.d("calendar", todaysDate + " " + currentTime);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete){
            Toast.makeText(this,"DELETE button is clicked",Toast.LENGTH_SHORT).show();

            onBackPressed();
        }
        if (item.getItemId() == R.id.save){
            note.setTitle(noteTitle.getText().toString());
            note.setContent(noteDetails.getText().toString());
            note.setDate(todaysDate);
            note.setTime(currentTime);
            int id = db.editNote(note);
            goToMain();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToMain() {
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private String pad(int i) {
        if (i<10)
            return "0"+i;
        return String.valueOf(i);
    }
}