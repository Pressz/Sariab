package com.gordarg.sariab.Presenters;

import com.gordarg.sariab.Views.ILoginView;

public class LoginPresenter implements ILoginPresenter{ // Presenter is somehow same as Controller in MVC


    private ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void doLogin(String username, String password) {
//  TODO:      User user = new User(username, password);
        switch (-1)
        {
            case -1:
                loginView.onLoginSuccess("ورود موفقیت آمیز بود :)");
                break;
            case 1:
                loginView.onLoginFailure("نام کاربری یا کلمه‌ی عبور معتبر نیست");

        }
    }

}
