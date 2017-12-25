package com.example.jose.aerisweatherapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.jose.aerisweatherapp.backend.AerisService;
import com.example.jose.aerisweatherapp.controller.AerisAdapter;
import com.example.jose.aerisweatherapp.controller.AutoValueGson_AerisTypeAdapterFactory;
import com.example.jose.aerisweatherapp.model.AerisPeriod;
import com.example.jose.aerisweatherapp.model.AerisResponse;
import com.example.jose.aerisweatherapp.view.DetailsFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Stack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private String ACCESS_ID = BuildConfig.AERIS_API_ACCESS_ID;
    private String AERIS_CLIENT_SECRET = BuildConfig.AERIS_API_SECRET_KEY;
    private String RESPONSE_TAG = "RESPONSE";
    private String BASE_URL = "http://api.aerisapi.com/";
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private AerisAdapter adapter;
    private List<AerisPeriod> listOfForecasts;
    private ImageView icon;
    public static boolean isMetricPressed = false;
    private FloatingActionButton converterButton;
    private FragmentManager fragmentManager;
    public static Stack<DetailsFragment> fragmentStack;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("INTEGER", "onCreate: " + Integer.valueOf('s'));

        converterButton = (FloatingActionButton) findViewById(R.id.converter_button);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        fragmentManager = getSupportFragmentManager();
        listOfForecasts = new ArrayList<>();
        adapter = new AerisAdapter(listOfForecasts);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(decoration);


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            makeRetrofitCall(BASE_URL, "new york,ny");
            return;
        }

        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    try {
                        Address address = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1).get(0);
                        String city = address.getLocality();
                        String state = address.getAdminArea();
                        if (city != null && state != null) {
                            String formattedCity = city + "," + state;
                            makeRetrofitCall(BASE_URL, formattedCity);
                        } else {
                            makeRetrofitCall(BASE_URL, "new york,ny");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        converterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertMeasurementSystem();
            }
        });

        fragmentStack = new Stack<>();
    }


    public void makeRetrofitCall(String baseUrl, String city) {

        GsonConverterFactory gsonConverterFactory = GsonConverterFactory
                .create(new GsonBuilder().registerTypeAdapterFactory(new AutoValueGson_AerisTypeAdapterFactory()).create());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverterFactory).build();

        AerisService service = retrofit.create(AerisService.class);
        Call<AerisResponse> call = service.getResponse(city, ACCESS_ID, AERIS_CLIENT_SECRET);
        Log.d("CALL", "makeRetrofitCall: " + call.request());
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
                fragmentTransaction.add(R.id.details_fragment_container, fragment, "details_fragment");
                fragmentTransaction.commit();
            }

            @Override
            public void onFailure(Call<AerisResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void convertMeasurementSystem() {
        DetailsFragment detailsFragment;
        if (fragmentManager.findFragmentByTag("clicked_fragment") == null)
            detailsFragment = (DetailsFragment) fragmentManager.findFragmentByTag("details_fragment");
        else
            detailsFragment = (DetailsFragment) fragmentManager.findFragmentByTag("clicked_fragment");
        adapter.notifyDataSetChanged();
        isMetricPressed = !isMetricPressed;
        if (isMetricPressed) {
            converterButton.setSelected(true);
            detailsFragment.refreshViews();
        } else {
            converterButton.setSelected(false);
            detailsFragment.refreshViews();
        }
    }


}
