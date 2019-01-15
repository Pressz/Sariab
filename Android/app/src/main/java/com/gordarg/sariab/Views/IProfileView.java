package com.gordarg.sariab.Views;

import com.gordarg.sariab.Models.User;

public interface IProfileView {
    void onBindData(User user);
    void onSubmitDataSucess(String message);
    void onSubmitDataFailed(String message);
}
