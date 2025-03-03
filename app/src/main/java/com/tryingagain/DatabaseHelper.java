package com.tryingagain;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String database = "userInfo.db";

    public DatabaseHelper(@Nullable Context context){
        super(context, "userInfo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table allusers(parentName TEXT primary key, childName TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop Table if exists allusers");
    }
/*
    public Boolean insertParentName(String pn){
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("parentName",pn);
        long result = MyDatabase.insert("allusers",null, contentValues);

        if(result == -1){
            return false;
        }
        return true;
    }
    public Boolean insertChildName(String cn){
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("childname",cn);
        long result = MyDatabase.insert("allusers",null, contentValues);

        if(result == -1){
            return false;
        }
        return true;
    }
*/

}
