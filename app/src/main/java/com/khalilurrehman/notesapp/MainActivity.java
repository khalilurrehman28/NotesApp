package com.khalilurrehman.notesapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.khalilurrehman.notesapp.Adapter.getNotesAdapter;
import com.khalilurrehman.notesapp.DBHelper.databaseHelper;
import com.khalilurrehman.notesapp.Helper.GridSpacingItemDecoration;
import com.khalilurrehman.notesapp.Helper.RecyclerTouchListener;
import com.khalilurrehman.notesapp.Models.NotesData;
import com.khalilurrehman.notesapp.NotesAlt.NotesAddUpdate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

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

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(1), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(notesAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, NotesAddUpdate.class).putExtra("note_id",0),add_notes);
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this,NotesAddUpdate.class);
                intent.putExtra("note_title",notesDataList.get(position).getTITLE());
                intent.putExtra("note_text",notesDataList.get(position).getNOTES());
                intent.putExtra("note_id",Integer.parseInt(notesDataList.get(position).getID()));
                startActivityForResult(intent,update_notes);
            }

            @Override
            public void onLongClick(View view, final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                // Add the buttons
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        Toast.makeText(MainActivity.this, "Deleted successfully", Toast.LENGTH_SHORT).show();
                        db.deleteData(Integer.parseInt(notesDataList.get(position).getID()));
                        readDataFromDB();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setTitle("Are you sure to delete?");
                dialog.setMessage(notesDataList.get(position).getTITLE());
                dialog.show();
            }
        }));
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
                    readDataFromDB();
                }
                break;
        }
    }

    private void readDataFromDB() {
        notesDataList.clear();
        Cursor cursor = db.getData();
        if (cursor.getCount() == 0){
            //noDataFound.setVisibility(View.VISIBLE);
            return;
        }

        //noDataFound.setVisibility(View.GONE);

        while (cursor.moveToNext()){
            notesDataList.add(new NotesData(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            notesAdapter.notifyDataSetChanged();
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        readDataFromDB();
    }
}
