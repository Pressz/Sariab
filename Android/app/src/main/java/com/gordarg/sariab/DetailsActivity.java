package com.gordarg.sariab;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gordarg.sariab.Models.Post;
import com.gordarg.sariab.Presenters.DetailsPresnter;
import com.gordarg.sariab.Presenters.IDetailsPresenter;
import com.gordarg.sariab.Views.IDetailsView;

import es.dmoral.toasty.Toasty;

public class DetailsActivity extends RexaBaseActivity implements IDetailsView {

    IDetailsPresenter detailsPresenter;

    ImageView iv;
    TextView title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        detailsPresenter = new DetailsPresnter(this);
        iv = (ImageView)findViewById(R.id.iv_detail);
        title = (TextView)findViewById(R.id.textView10);
        description = (TextView)findViewById(R.id.textView6);
        detailsPresenter.doDownload(getIntent().getStringExtra("ID"));
    }

    @Override
    public void onDownloadFoodMeta(Post food) {

        if (food.getBinImage() != null)
            iv.setImageBitmap(food.getBinImage());
        title.setText(food.getTitle());
        description.setText(food.getDescription());
    }

    @Override
    public void onDownloadFoodMetaFailed(String Message)
    {
        finish();
        Toasty.error(this, Message, Toast.LENGTH_LONG).show();
    }


}
