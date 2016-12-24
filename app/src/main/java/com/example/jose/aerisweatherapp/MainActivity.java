package com.example.jose.aerisweatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.example.jose.aerisweatherapp.backend.AerisService;
import com.example.jose.aerisweatherapp.controller.AerisAdapter;
import com.example.jose.aerisweatherapp.model.AerisPeriod;
import com.example.jose.aerisweatherapp.model.AerisResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    //TODO create detailed view fragment
    //TODO add toggle for celsius

    private String ACCESS_ID = BuildConfig.AERIS_API_ACCESS_ID;
    private String AERIS_CLIENT_SECRET = BuildConfig.AERIS_API_SECRET_KEY;
    private String RESPONSE_TAG = "RESPONSE";
    private String BASE_URL = "http://api.aerisapi.com/forecasts/";
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private AerisAdapter adapter;
    private List<AerisPeriod> listOfForecasts;
    private ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        listOfForecasts = new ArrayList<>();
        adapter = new AerisAdapter(listOfForecasts);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        makeRetrofitCall(BASE_URL);
    }

    public void makeRetrofitCall(String baseUrl){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        AerisService service = retrofit.create(AerisService.class);
        Call<AerisResponse> call = service.getResponse();
        call.enqueue(new Callback<AerisResponse>() {
            @Override
            public void onResponse(Call<AerisResponse> call, Response<AerisResponse> response) {
                listOfForecasts = response.body().getResponse().get(0).getPeriod();
                adapter.setData(listOfForecasts);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<AerisResponse> call, Throwable t) {
                Log.i(RESPONSE_TAG,t.getMessage());
            }
        });
    }
}
