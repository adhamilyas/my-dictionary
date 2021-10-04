package com.example.mydictionary2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceAPI {
    @GET("words")
    Call<List<DataWord>> getWord(@Query("q") String q);
}
