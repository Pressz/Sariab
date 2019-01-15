package com.gordarg.sariab.Helpers;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.List;

public class IntentCheck {

    Context context;
    Intent intent;

    public IntentCheck(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
    }

    public boolean isIntentAvailable() {
        List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
}
