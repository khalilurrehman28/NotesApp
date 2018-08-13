package com.khalilurrehman.notesapp.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseHelper extends SQLiteOpenHelper {

    private static final String  DATABASE_NAME = "NOTES_DB";
    private static final String NOTES_TBL = "NOTES_TBL";
    private static final String ID = "ID";
    private static final String TITLE = "TITLE";
    private static final String NOTES = "NOTES";
    private static final String DATE = "DATE";

    public databaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL = "CREATE TABLE "+NOTES_TBL+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT , "+TITLE+" TEXT,"+ NOTES+" TEXT ,"+DATE+" DATETIME)";
        sqLiteDatabase.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ NOTES_TBL);
        onCreate(sqLiteDatabase);
    }
}
