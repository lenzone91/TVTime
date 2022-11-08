package com.example.tvtime.network;

import android.database.Observable;

import org.json.JSONArray;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("search")
    Observable<JSONArray> searchMovies(@Query("query") String query);

}