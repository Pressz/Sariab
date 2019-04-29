package com.gordarg.sariab.Data;

import android.database.Cursor;
import android.os.AsyncTask;

import com.gordarg.sariab.Configuration.Network;
import com.gordarg.sariab.Network.Rest;
import com.gordarg.sariab.Network.Rss;
import com.gordarg.sariab.SQLite.TABLE_Cache;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.json.JSONException;
import org.w3c.dom.Document;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Access {

    public Access(String controller, String parameters) {
        this.controller = controller;
        this.parameters = parameters;
    }

    private String controller;
    private String parameters;

    // Alternative for Get() which allows us to download xml
    public String GetRss() throws ExecutionException, InterruptedException, NullPointerException, JSONException {

        String url = Network.rss_end_point.toString();
        String response = new GetRequestXml().execute(url).get().getResult();


        try {
            JSONObject xmlJSONObj = XML.toJSONObject( response);
            String jsonPrettyPrintString = xmlJSONObj.toString(4);
            System.out.println(jsonPrettyPrintString);
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }

        return null;

//        JSONObject FuckMeHard = XML.toJSONObject(response);
//
//        String json = FuckMeHard.toString();
//        return json;

        // XML -> JSON
        // JSON -> Json Array


    }

    public JSONArray Get() throws ExecutionException, InterruptedException, JSONException, NullPointerException {
        String query = controller + parameters;

        String response = new GetRequestRest().execute(query).get().getResult();
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
    public class GetRequestRest extends AsyncTask<String, Void, AsyncTaskResult<String>> {
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

    // Dark Code
    public class GetRequestXml extends AsyncTask<String, Void, AsyncTaskResult<String>> {
        @Override
        protected AsyncTaskResult<String> doInBackground(String... params) {

            try {
                Rss r = new Rss(params[0]);
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
