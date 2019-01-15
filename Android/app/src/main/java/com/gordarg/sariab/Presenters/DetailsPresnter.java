package com.gordarg.sariab.Presenters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;

import com.gordarg.sariab.Data.Access;
import com.gordarg.sariab.Models.Post;
import com.gordarg.sariab.Views.IDetailsView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class DetailsPresnter implements IDetailsPresenter {

    private IDetailsView detailsView;

    public DetailsPresnter(IDetailsView detailsView) {
        this.detailsView = detailsView;
    }

    @Override
    public void doDownload(String Id) {
        Post output = null;

        try {
            Access acc = new Access("postController.php", "?BinImage=✓&Id=" + Id);
            JSONArray response = acc.Get();

            JSONArray resp = acc.Get();
            if (resp != null) {
                JSONObject jo = (JSONObject) resp.get(0);
                String b = jo.getString("BinImage");
                byte[] decodedString = Base64.decode(b, Base64.DEFAULT);
                final Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                if (b.equals("null") || decodedByte.equals("") || b.equals("✓"))
//                    return;
                output = new Post(Id,
                        jo.getString("Title"),
                        jo.getString("Description"),
                        decodedByte
                        );
            }
            final Post finalOutput = output;
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    detailsView.onDownloadFoodMeta(finalOutput);
                }
            });

        } catch (ExecutionException e) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
//                    detailsView.onDownloadFoodMetaFailed("اجرا با شکست مواجه شد");
                }});
        } catch (InterruptedException e) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
//                  detailsView.onDownloadFoodMetaFailed("فرایند نیمه‌کاره ماند");
                }});
        } catch (JSONException e) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
//                   detailsView.onDownloadFoodMetaFailed("داده نامعتبر از سرور دریافت شد");
                }});
        } catch (NullPointerException e) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    detailsView.onDownloadFoodMetaFailed("داده‌ای دریافت نشد");
                }});
        }
    }
}
