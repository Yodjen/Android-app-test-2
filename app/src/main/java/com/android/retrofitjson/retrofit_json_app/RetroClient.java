package com.android.retrofitjson.retrofit_json_app;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by batsa on 15.02.2017.
 */

public class RetroClient {

    /**
     *  URLS
     */
    private static final String ROOT_URL = "https://api.myjson.com/bins/7tghr";

    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
