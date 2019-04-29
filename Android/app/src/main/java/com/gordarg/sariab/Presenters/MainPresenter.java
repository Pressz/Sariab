package com.gordarg.sariab.Presenters;

import android.os.Handler;
import android.os.Looper;

import com.gordarg.sariab.Data.Access;
import com.gordarg.sariab.Models.Post;
import com.gordarg.sariab.Views.IMainView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainPresenter implements IMainPresenter {

    IMainView mainView;

    public MainPresenter(IMainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void doDownloadPost(){
        Access acc = new Access(null, null);

        try {
            JSONArray response = acc.GetRss();
            doDownloadList(response);
        }
        catch (ExecutionException e) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    mainView.onBindListError("اجرا با شکست مواجه شد");
                }});
        } catch (InterruptedException e) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    mainView.onBindListError("فرایند نیمه‌کاره ماند");
                }});
        } catch (JSONException e) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    mainView.onBindListError("داده نامعتبر از سرور دریافت شد");
                }});
        } catch (NullPointerException e) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    mainView.onBindListError("داده‌ای دریافت نشد");
                }});
        }
    }

    @Override
    public void doDownloadList(JSONArray response){

        if (response == null)
        {
            // TODO: Error
            return;
        }

        ArrayList<Post> postList = new ArrayList<Post>();
        for (int i = 0 ; i < response.length() ; i++)
        {
            try {
                JSONObject jo = (JSONObject) response.get(i);
                postList.add(
                        new Post(
                            null,
                            jo.getString("title") + "",
                            jo.getString("description") + "",
                            null

                        )
                );
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mainView.onBindLists(postList);
    }
}
