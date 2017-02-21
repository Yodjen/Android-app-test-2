package com.android.retrofitjson.retrofit_json_app.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;

/**
 * Created by batsa on 20.02.2017.
 */

public class InternetConnection {

    /**
     * CHECK WHETHER INTERNET CONNECTION AVAILABLE OR NOT
     */
    public static boolean ckeckConnection(@NonNull Context context){
        return ( (ConnectivityManager) context.getSystemService
                (Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }
}
