package com.gordarg.sariab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.gordarg.sariab.Data.Access;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

public class TestActivity extends RexaBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        Access access = new Access(null, null);
        try {
            String response  = access.GetRss().toString(4);
            TextView tv3 = findViewById(R.id.textView3);
            tv3.setText(response);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
