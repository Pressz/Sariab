package com.gordarg.sariab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.gordarg.sariab.Data.BigBrother;
import com.gordarg.sariab.Helpers.FontsOverride;
import com.gordarg.sariab.Presenters.RexaBasePresenter;
import com.gordarg.sariab.SQLite.TABLE_Cache;
import com.gordarg.sariab.Views.RexaBaseView;

import es.dmoral.toasty.Toasty;

class RexaBaseActivity extends AppCompatActivity implements RexaBaseView {

    protected RexaBasePresenter rexaBasePresenter;

    protected DrawerLayout fullLayout;
    protected FrameLayout actContent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        rexaBasePresenter = new RexaBasePresenter(this);

        new BigBrother().setCache(new TABLE_Cache(getApplicationContext()));
        // String s = ((MyApplication) this.getApplication()).getSomeVariable();
    }

    @Override
    public void setContentView(final int layoutResID) {

        fullLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_rexabase, null);
        actContent = (FrameLayout) fullLayout.findViewById(R.id.content);

        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/behdad.otf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/behdad.otf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/behdad.otf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/behdad.otf");

        getLayoutInflater().inflate(layoutResID, actContent, true);
        super.setContentView(fullLayout);
    }

    @Override
    public void onNetworkTestFailure(String message) {
        Toasty.error(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNetworkTestSuccess(String message) {
        Toasty.success(this, message, Toast.LENGTH_LONG).show();
    }
}