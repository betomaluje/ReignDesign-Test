package com.betomaluje.android.reigndesigntest.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by betomaluje on 3/12/16.
 */
public class InternetUtils {

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

}
