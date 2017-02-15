package com.android.retrofitjson.retrofit_json_app;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by batsa on 16.02.2017.
 */
public interface ApiService {
    /**
     *  GET annotation from our URL
     *
     */
    @GET("/bins/7tghr")
    Call<ContactList> getMyJSON();
}
