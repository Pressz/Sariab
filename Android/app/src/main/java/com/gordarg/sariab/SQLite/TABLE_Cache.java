package com.gordarg.sariab.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class TABLE_Cache extends DBHelper{
    public TABLE_Cache(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }

    public static final String TABLE_NAME = "Cache";
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_URL = "Url";
    public static final String COLUMN_RESPONSE = "Response";
    public static final String COLUMN_METHOD = "Method";

    public boolean insert (String url, String response, String method) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_URL, url);
        contentValues.put(COLUMN_RESPONSE, response);
        contentValues.put(COLUMN_METHOD, method);
        db.insert(TABLE_NAME, null, contentValues);

        // TODO: Remove previous cahced verisons // Because of storage and nothing else =)

        return true;
    }

    public Cursor get(String Url) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_URL + "='" + Url + "' ORDER BY " + COLUMN_ID + " LIMIT 1"
                , null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME );
        return numRows;
    }

    public boolean update (Integer id,String url, String response, String method) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_URL, url);
        contentValues.put(COLUMN_RESPONSE, response);
        contentValues.put(COLUMN_METHOD, method);
        db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer delete (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                COLUMN_ID + " = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAll() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(TABLE_NAME)));
            res.moveToNext();
        }
        return array_list;
    }



}
