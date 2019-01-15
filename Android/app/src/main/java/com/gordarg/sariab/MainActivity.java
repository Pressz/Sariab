package com.gordarg.sariab;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.gordarg.sariab.Fragments.FragmentA;
import com.gordarg.sariab.Fragments.FragmentB;
import com.gordarg.sariab.Fragments.FragmentC;
import com.gordarg.sariab.Fragments.FragmentD;
import com.gordarg.sariab.Models.Post;
import com.gordarg.sariab.Models.User;
import com.gordarg.sariab.Presenters.MainPresenter;
import com.gordarg.sariab.Views.IMainView;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import eu.long1.spacetablayout.SpaceTabLayout;

public class MainActivity extends RexaBaseActivity implements IMainView {

    SpaceTabLayout tabLayout;
    Bundle _savedInstanceState;

    static User loggedin_user;

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
    public void onBindLists(final ArrayList<Post> foodListA, final ArrayList<Post> foodListB, final ArrayList<Post> foodListC, final ArrayList<Post> foodListD) {

        List<Fragment> fragmentList = new ArrayList<>();
        Bundle bundle;

        FragmentA fragmentA = new FragmentA();
        bundle = new Bundle();
        bundle.putSerializable("list", foodListA);
        fragmentA.setArguments(bundle);
        fragmentList.add(fragmentA);

        FragmentB fragmentB = new FragmentB();
        bundle = new Bundle();
        bundle.putSerializable("list", foodListB);
        fragmentB.setArguments(bundle);
        fragmentList.add(fragmentB);

        FragmentC fragmentC = new FragmentC();
        bundle = new Bundle();
        bundle.putSerializable("list", foodListC);
        fragmentC.setArguments(bundle);
        fragmentList.add(fragmentC);

        FragmentD fragmentD = new FragmentD();
        bundle = new Bundle();
        bundle.putSerializable("list", foodListD);
        fragmentD.setArguments(bundle);
        fragmentList.add(fragmentD);


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (SpaceTabLayout) findViewById(R.id.spaceTabLayout);
        tabLayout.initialize(viewPager, getSupportFragmentManager(),
                fragmentList, _savedInstanceState);

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
        finish();
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

}