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
    public void doDownloadFood(){
        Access acc = new Access("postController.php", "");

        try {
            JSONArray response = acc.Get();
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

        ArrayList<Post> foodListA = new ArrayList<Post>();
        int i = 0;
        if (response != null)
            for (i = 0 ; i < response.length() ; i++)
            {
                try {
                    JSONObject jo = (JSONObject) response.get(i);
                    switch (jo.getString("Type")) {
                        case "scie":
                            foodListA.add(new Post(jo.getString("Id"),jo.getString("Title") + "", jo.getString("Description") + "", null));
                            break;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        mainView.onBindLists(foodListA);
    }
}
