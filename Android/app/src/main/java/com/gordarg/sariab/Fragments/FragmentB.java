package com.gordarg.sariab.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gordarg.sariab.Adapters.PostAdapter;
import com.gordarg.sariab.Models.Post;
import com.gordarg.sariab.R;

import java.util.ArrayList;


public class FragmentB extends Fragment {

    RecyclerView recyclerView;
    PostAdapter adapter;
    static ArrayList<Post> foodList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View main = inflater.inflate(R.layout.fragment_b, container, false);


        if (foodList == null)
            foodList = (ArrayList<Post>) getArguments().getSerializable("list");
        getArguments().remove("list");

        adapter = new PostAdapter(main.getContext(), foodList);

        recyclerView = (RecyclerView)main.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(main.getContext()));

        recyclerView.setAdapter(adapter);

        return main;
    }


}

