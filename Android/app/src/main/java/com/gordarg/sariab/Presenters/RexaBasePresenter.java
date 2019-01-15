package com.gordarg.sariab.Presenters;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.gordarg.sariab.Network.Rest;
import com.gordarg.sariab.Views.RexaBaseView;

import java.io.IOException;

public class RexaBasePresenter implements IRexaBasePresenter {

    RexaBaseView rexaBaseView;

    public RexaBasePresenter(RexaBaseView rexaBaseView) {
        this.rexaBaseView = rexaBaseView;
    }

    @Override
    public void doNetworkTest() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Rest r = new Rest("BridgeController.php");
                    final short Status = r.testConnection();
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            switch (Status) {
                                case (200):
                                    rexaBaseView.onNetworkTestSuccess("ارتباط با سرور برقرار است");
                                    break;
                                case (403):
                                    rexaBaseView.onNetworkTestFailure("خطا در احراز هویت");
                                    break;
                                case (404):
                                    rexaBaseView.onNetworkTestFailure("آدرس یافت نشد");
                                    break;
                                default:
                                    rexaBaseView.onNetworkTestFailure("ارتباط با خطا مواجه شد: " + String.valueOf(Status));
                            }
                        }
                    });
                } catch (IOException e) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            rexaBaseView.onNetworkTestFailure("ارتباط با سرور برقرار نیست");
                        }
                    });
                }
            }
        });
    }
}