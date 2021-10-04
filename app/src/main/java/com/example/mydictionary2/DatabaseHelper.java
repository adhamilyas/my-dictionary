package com.example.mydictionary2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FavoritesWords";
    private static final String TABLE_WORD = "Words";
    private static final String KEY_NAME = "word";

    public DatabaseHelper(@Nullable Context ctx) {
        super(ctx, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_WORD + "(id INTEGER PRIMARY KEY," + KEY_NAME + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORD);
        onCreate(db);
    }

    public void addFavorites(String word){
        if(getUserModelCount(word) == 0){
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_NAME,word);
            db.insert(TABLE_WORD,null,values);
            db.close();
        }
    }

    public void deleteFavorites(String word){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_WORD,KEY_NAME + "= ?", new String[]{word});
        db.close();
    }

    public int getUserModelCount(String word) {
        String countQuery = "SELECT  * FROM " + TABLE_WORD + " WHERE " + KEY_NAME + " = '" + word + "'";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public List<String> getWords(){
        List<String> words = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_WORD;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                words.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return words;
    }
}
