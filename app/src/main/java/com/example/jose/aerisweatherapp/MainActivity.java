package com.example.jose.aerisweatherapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.jose.aerisweatherapp.backend.AerisService;
import com.example.jose.aerisweatherapp.controller.AerisAdapter;
import com.example.jose.aerisweatherapp.model.AerisPeriod;
import com.example.jose.aerisweatherapp.model.AerisResponse;
import com.example.jose.aerisweatherapp.view.DetailsFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
    public static boolean isMetricPressed = false;
    private Button converterButton;
    private FragmentManager fragmentManager;
    public static Stack<DetailsFragment> fragmentStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        fragmentManager = getSupportFragmentManager();
        listOfForecasts = new ArrayList<>();
        adapter = new AerisAdapter(listOfForecasts);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        converterButton = (Button) findViewById(R.id.converter_button);
        makeRetrofitCall(BASE_URL);
        converterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertMeasurementSystem();
            }
        });
        fragmentStack = new Stack<>();
    }

    public void makeRetrofitCall(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        AerisService service = retrofit.create(AerisService.class);
        Call<AerisResponse> call = service.getResponse();
        call.enqueue(new Callback<AerisResponse>() {
            @Override
            public void onResponse(Call<AerisResponse> call, Response<AerisResponse> response) {
                listOfForecasts = response.body().getResponse().get(0).getPeriod();
                adapter.setData(listOfForecasts);
                adapter.notifyDataSetChanged();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                AerisPeriod period = listOfForecasts.get(0);
                bundle.putParcelable("data", period);
                DetailsFragment fragment = new DetailsFragment();
                fragment.setArguments(bundle);
                fragmentTransaction.add(R.id.details_fragment_container, fragment, "details_fragment_tag");
                fragmentTransaction.commit();
            }

            @Override
            public void onFailure(Call<AerisResponse> call, Throwable t) {
                Log.i(RESPONSE_TAG, t.getMessage());
            }
        });
    }

    public void convertMeasurementSystem() {
        DetailsFragment detailsFragment = (DetailsFragment) fragmentManager.findFragmentByTag("clicked_fragment");
        adapter.notifyDataSetChanged();
        isMetricPressed = !isMetricPressed;
        if (isMetricPressed) {
            converterButton.setText(R.string.converter_button_decimal_text);
            converterButton.setSelected(true);
            detailsFragment.refreshViews();
        } else {
            converterButton.setText(R.string.converter_button_metric_text);
            converterButton.setSelected(false);
            detailsFragment.refreshViews();
        }
    }
}
