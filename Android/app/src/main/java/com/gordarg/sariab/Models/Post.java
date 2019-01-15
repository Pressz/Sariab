package com.gordarg.sariab.Models;

import android.graphics.Bitmap;

public class Post implements IPost {

    private String id;
    private String title;
    private String description;
    private Bitmap binImage;

    public Post(String id, String title, String description, Bitmap binImage) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.binImage = binImage;
    }

    @Override
    public String getId() {
        return id;
    }
    @Override
    public String getTitle() { return title; }
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public Bitmap getBinImage() {
        return binImage;
    }

}
