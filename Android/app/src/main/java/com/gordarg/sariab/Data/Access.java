package com.gordarg.sariab.Data;

import android.database.Cursor;
import android.os.AsyncTask;

import com.gordarg.sariab.Network.Rest;
import com.gordarg.sariab.SQLite.TABLE_Cache;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Access {

    public Access(String controller, String parameters) {
        this.controller = controller;
        this.parameters = parameters;
    }

    private String controller;
    private String parameters;


    public JSONArray Get() throws ExecutionException, InterruptedException, JSONException, NullPointerException {
        String url = controller + parameters;

        String response = new GetRequest().execute(url).get().getResult();
//        if (response.equals("null"))
//            return new JSONArray("[]");
        if (response == null)
            throw new NullPointerException();
        if (response.charAt(0) != '[')
            response = "[" + response + "]";
        return new JSONArray(response);
    }


    public JSONArray Post() {
        String url = controller + parameters;
        // TODO

        return null;
    }

    // Dark Code
    public class GetRequest extends AsyncTask<String, Void, AsyncTaskResult<String>> {
        @Override
        protected AsyncTaskResult<String> doInBackground(String... params) {

            try {
                Rest r = new Rest(params[0]);
                String output = r.get();
                if (output == null || output.equals(""))
                    throw new IOException();
                TABLE_Cache db = new BigBrother().getCache();
                db.insert(params[0], output, "GET");
                return new AsyncTaskResult<String>(output);
            } catch (IOException exp) {
                TABLE_Cache db = new BigBrother().getCache();
                Cursor cursor = db.get(params[0]);
                String r = "";
                if (cursor.moveToFirst()) {
                    do {
                        r = cursor.getString(cursor.getColumnIndex(TABLE_Cache.COLUMN_RESPONSE));
                    } while (cursor.moveToNext());
                } else
                    return null;
                cursor.close();
                return new AsyncTaskResult<String>(r);
            }
        }
    }
}
