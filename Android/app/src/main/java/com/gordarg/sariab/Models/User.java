package com.gordarg.sariab.Models;

import android.graphics.Bitmap;

import java.io.Serializable;

public class User implements IUser, Serializable {

    // Define props
    private int id;
    private String username, firstname, lastname, register, hashPassword;
    private Bitmap binImage;

    public User(int id, String username, String firstname, String lastname, String register, String hashPassword, Bitmap binImage) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.register = register;
        this.hashPassword = hashPassword;
        this.binImage = binImage;
    }


    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Bitmap getBinImage() {
        return binImage;
    }

    @Override
    public String getFirstname() {
        return firstname;
    }

    @Override
    public String getLastname() {
        return lastname;
    }

    @Override
    public String getRegister() {
        return register;
    }

    @Override
    public String getHashPassword() {
        return hashPassword;
    }
}
