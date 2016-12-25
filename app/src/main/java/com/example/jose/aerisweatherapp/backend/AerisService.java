package com.example.jose.aerisweatherapp.backend;

import com.example.jose.aerisweatherapp.model.AerisResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface AerisService {
    //http://api.aerisapi.com/forecasts/buffalo,ny?client_id=CSmwtNoZifPD0iecEwoZ6&client_secret=wSYggsNKxAvK7VJlBEYGN121Hldu9D6VOXvhOxAA

    @GET("buffalo,ny?client_id=CSmwtNoZifPD0iecEwoZ6&client_secret=wSYggsNKxAvK7VJlBEYGN121Hldu9D6VOXvhOxAA")
    Call<AerisResponse> getResponse();
}
