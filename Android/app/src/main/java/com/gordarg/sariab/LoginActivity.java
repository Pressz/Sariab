package com.gordarg.sariab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gordarg.sariab.Presenters.LoginPresenter;
import com.gordarg.sariab.Views.ILoginView;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends RexaBaseActivity implements ILoginView {

    ViewPager mViewPager;
    LoginPresenter loginPresenter;

    EditText phone, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenter(this);

        final LoginPresenter loginPresenter = new LoginPresenter(this);
        phone = (findViewById(R.id.editText));
        pass = (findViewById(R.id.editText2));

        findViewById(R.id.button_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.doLogin(phone.getText().toString(), pass.getText().toString());
            }
        });
        findViewById(R.id.button_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onLoginSuccess(String message) {
        Toasty.success(this, message, Toast.LENGTH_SHORT).show();

        Intent intent =
                new Intent(LoginActivity.this
                , MainActivity.class);

// TODO:
//        User loggedin_user = new User(String.valueOf(phone.getText()), String.valueOf(pass.getText()));
//        intent.putExtra("LOGGEDINUSER", (Serializable) loggedin_user);
        startActivity(intent);
    }

    @Override
    public void onLoginFailure(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNetworkOperationFailure(String message) {
        Toasty.error(this, message, Toast.LENGTH_LONG).show();
    }
}
