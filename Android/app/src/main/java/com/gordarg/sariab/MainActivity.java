package com.gordarg.sariab;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.gordarg.sariab.Adapters.PostAdapter;
import com.gordarg.sariab.Models.Post;
import com.gordarg.sariab.Models.User;
import com.gordarg.sariab.Presenters.MainPresenter;
import com.gordarg.sariab.Views.IMainView;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class MainActivity extends RexaBaseActivity implements IMainView {

    Bundle _savedInstanceState;

    static User loggedin_user;

    PostAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _savedInstanceState = savedInstanceState;
        setContentView(R.layout.activity_main);

// TODO:        loggedin_user =  (User) getIntent().getSerializableExtra("LOGGEDINUSER");

        ((FloatingActionButton)findViewById(R.id.fab)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.success(getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        final MainPresenter mainPresenter = new MainPresenter(this);
        mainPresenter.doDownloadFood();

    }



    @Override
    public void onBindLists(final ArrayList<Post> posts) {


        // Fake data
        // TODO: This segment is just for test purpose only
        // And it must be removed
        ArrayList<Post> foodList = new ArrayList<Post>();
        foodList.add(new Post("1", "Title 1 ", "Description 1 ", null));
        foodList.add(new Post("1", "Title 1 ", "Description 1 ", null));
        foodList.add(new Post("1", "Title 1 ", "Description 1 ", null));
        foodList.add(new Post("1", "Title 1 ", "Description 1 ", null));


//        if (foodList == null)
//            foodList = (ArrayList<Post>) getArguments().getSerializable("list");
//        getArguments().remove("list");
        adapter = new PostAdapter(getApplicationContext(), foodList);


        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerView.setAdapter(adapter);

    }


    //we need the outState to save the position
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // tabLayout.saveState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBindListError(String message) {
        // TODO: Open Settings startActivity(new  );
//        finish();
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

}