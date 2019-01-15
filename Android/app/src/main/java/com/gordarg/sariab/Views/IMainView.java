package com.gordarg.sariab.Views;

import com.gordarg.sariab.Models.Post;

import java.util.ArrayList;

public interface IMainView {
    void onBindLists(ArrayList<Post> foodListA, ArrayList<Post> foodListB, ArrayList<Post> foodListC, ArrayList<Post> foodListD);
    void onBindListError(String message);
}
