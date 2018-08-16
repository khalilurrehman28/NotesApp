package com.khalilurrehman.notesapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.khalilurrehman.notesapp.Adapter.getNotesAdapter;
import com.khalilurrehman.notesapp.DBHelper.databaseHelper;
import com.khalilurrehman.notesapp.Helper.GridSpacingItemDecoration;
import com.khalilurrehman.notesapp.Models.NotesData;
import com.khalilurrehman.notesapp.NotesAlt.NotesAddUpdate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    databaseHelper db;
    getNotesAdapter notesAdapter;
    List<NotesData> notesDataList;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    private static final int add_notes = 100;
    private static final int update_notes = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new databaseHelper(this);
        notesDataList = new ArrayList<>();
        notesAdapter = new getNotesAdapter(this,notesDataList);
        recyclerView = findViewById(R.id.recyclerView);
        floatingActionButton = findViewById(R.id.fab);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(1), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(notesAdapter);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, NotesAddUpdate.class).putExtra("note_id",0),add_notes);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case add_notes:
                if (resultCode==RESULT_OK){
                    assert data != null;
                    if(db.insertData(data.getStringExtra("note_title"),data.getStringExtra("note_text"))){
                        Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();
                        readDataFromDB();
                    }

                }
                break;
            case update_notes:
                if (resultCode == RESULT_OK){
                    assert data != null;
                    db.updateData(data.getStringExtra("note_title"),data.getStringExtra("note_text"),data.getIntExtra("note_id",0));
                }
                break;
        }
    }

    private void readDataFromDB() {

    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
