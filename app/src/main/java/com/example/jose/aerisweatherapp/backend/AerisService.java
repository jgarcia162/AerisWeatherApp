package com.example.jose.aerisweatherapp.backend;

import com.example.jose.aerisweatherapp.model.AerisResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface AerisService {

    @GET("forecasts/{city}")
    Call<AerisResponse> getResponse(@Path("city") String city,@Query("client_id") String clientId, @Query("client_secret") String clientSecret);
}
