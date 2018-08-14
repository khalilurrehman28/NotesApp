package com.khalilurrehman.notesapp.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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


    public boolean insertData(String text,String Content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE,text);
        contentValues.put(NOTES,Content);
        contentValues.put(DATE,getDateTime());
        long result = db.insert(NOTES_TBL,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public void updateData(String title,String content, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE,title); //These Fields should be your String values of actual column names
        cv.put(NOTES,content);
        cv.put(DATE,getDateTime());
        db.update(NOTES_TBL, cv, ID+"="+id, null);
    }

    public void deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery("DELETE * FROM "+ NOTES_TBL+" WHERE "+ID+"="+id+" ",null);
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM "+ NOTES_TBL,null);
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
