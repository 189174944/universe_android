package com.fullstackvalley.fragmenttest.http;

import com.fullstackvalley.fragmenttest.config.Config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {
    //接口A
    public static Retrofit getMainRetrofit() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        return new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    //接口B
    public static Retrofit getJokeRetrofit() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        return new Retrofit.Builder()
                .baseUrl(Config.jssjHost)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
