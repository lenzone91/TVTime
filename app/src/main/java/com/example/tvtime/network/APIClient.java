package com.example.tvtime.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private final static String BASE_URL = "https://fake-movie-database-api.herokuapp.com/api";

    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }
}