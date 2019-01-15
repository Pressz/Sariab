package com.gordarg.sariab.Models;

import android.graphics.Bitmap;

public interface IUser {
    int getId();
    String getUsername();
    Bitmap getBinImage();
    String getFirstname();
    String getLastname();
    String getRegister();
    String getHashPassword();

}
