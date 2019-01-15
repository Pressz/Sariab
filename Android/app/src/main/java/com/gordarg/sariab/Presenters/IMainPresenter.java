package com.gordarg.sariab.Presenters;

import org.json.JSONArray;

public interface IMainPresenter {
    void doDownloadList(JSONArray response);
    void doDownloadFood();
}
