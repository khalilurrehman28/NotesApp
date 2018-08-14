package com.khalilurrehman.notesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.khalilurrehman.notesapp.DBHelper.databaseHelper;

public class MainActivity extends AppCompatActivity {

    databaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new databaseHelper(this);

    }
}
