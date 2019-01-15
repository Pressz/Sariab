package com.gordarg.sariab.Helpers;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.lang.reflect.Field;

/**
 * Created by vamsi on 06-05-2017 for Android custom font article
 */
//TODO: Rexa
public class FontsOverride {
    public static void setDefaultFont(Context context, String staticTypefaceFieldName, String fontAssetName) {
        try {
            AssetManager asse = context.getAssets();
            final Typeface regular = Typeface.createFromAsset(asse,
                    fontAssetName);
            replaceFont(staticTypefaceFieldName, regular);
        }
        catch (Exception e ){
            e.printStackTrace();
        }
    }

    protected static void replaceFont(String staticTypefaceFieldName,
                                      final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}