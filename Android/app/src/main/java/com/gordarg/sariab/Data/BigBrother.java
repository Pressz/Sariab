package com.gordarg.sariab.Data;

import android.app.Application;

import com.gordarg.sariab.SQLite.TABLE_Cache;
import com.gordarg.sariab.SQLite.TABLE_Orders;

public class BigBrother extends Application {
    static private TABLE_Cache cache;
    static private TABLE_Orders orders;

    public TABLE_Cache getCache() {
        return cache;
    }

    public void setCache(TABLE_Cache In) {
        cache = In;
    }
}
