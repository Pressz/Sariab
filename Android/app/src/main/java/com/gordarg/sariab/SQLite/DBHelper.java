package com.gordarg.sariab.SQLite;

import java.util.HashMap;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import com.gordarg.sariab.Configuration.App;

public class DBHelper extends SQLiteOpenHelper {


    // /data/data/com.gordarg.sariab/databases/sariab.db

    public static final String DATABASE_NAME = "sariab.db";

    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, App.dbversion.toInt());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_Orders.TABLE_NAME + " (" + TABLE_Orders.COLUMN_ID + " integer primary key, " + TABLE_Orders.COLUMN_FOODID + " text," + TABLE_Orders.COLUMN_COUNT + " int)");
        db.execSQL("CREATE TABLE " + TABLE_Cache.TABLE_NAME + " (" + TABLE_Cache.COLUMN_ID + " integer primary key, " + TABLE_Cache.COLUMN_URL + " text," +TABLE_Cache.COLUMN_RESPONSE + " text," + TABLE_Cache.COLUMN_METHOD + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Orders.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Cache.TABLE_NAME);
        onCreate(db);
    }


}