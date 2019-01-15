package com.gordarg.sariab;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gordarg.sariab.Models.User;
import com.gordarg.sariab.Presenters.IProfilePresenter;
import com.gordarg.sariab.Presenters.ProfilePresenter;
import com.gordarg.sariab.Views.IProfileView;

import es.dmoral.toasty.Toasty;

public class ProfileActivity extends RexaBaseActivity implements IProfileView {

    IProfilePresenter profilePresenter;


    EditText Username, Firstname, Lastname, Password, Age;
    TextView Register;
    Spinner City, Sex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
        rexaBasePresenter.doNetworkTest();
        profilePresenter = new ProfilePresenter();

        Username = findViewById(R.id.text_Username);
        Firstname = findViewById(R.id.text_Firstname);
        Lastname = findViewById(R.id.text_Lastname);
        Password = findViewById(R.id.text_Password);
        Age = findViewById(R.id.text_Age);
        City = findViewById(R.id.spin_City);
        Sex = findViewById(R.id.spin_Sex);
        Register = findViewById(R.id.text_Register);


        findViewById(R.id.button_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO if pass and confirm matches

            profilePresenter.doSubmitData(new User(
                    0,
                    Username.getText().toString(),
                    Firstname.getText().toString(),
                    Lastname.getText().toString(),
                    Register.getText().toString(),
                    Password.getText().toString(),
                    null // TODO
            ));

            }
        });
    }

    @Override
    public void onBindData(User user) {
        Username.setText(user.getUsername());
        Firstname.setText(user.getUsername());
        Lastname.setText(user.getUsername());
        Register.setText(user.getUsername());
    }

    @Override
    public void onSubmitDataSucess(String message) {
        Toasty.success(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSubmitDataFailed(String message) {
        Toasty.error(this, message, Toast.LENGTH_LONG).show();
    }
}
