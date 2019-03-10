package com.gordarg.sariab.Views;

import com.gordarg.sariab.Models.Post;

import java.util.ArrayList;

public interface IMainView {
    void onBindLists(ArrayList<Post> foodListA);
    void onBindListError(String message);
}
