package com.gordarg.sariab.Views;

public interface ILoginView {
    void onLoginSuccess(String message);
    void onLoginFailure(String message);
    void onNetworkOperationFailure(String message);
}
