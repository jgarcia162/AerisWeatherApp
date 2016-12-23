package com.example.jose.aerisweatherapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Joe on 12/22/16.
 */
public class AerisLocation {
    private float lat;
    @SerializedName("long")
    private float lon;

    public float getLatitude() {
        return lat;
    }

    public float getLongitude() {
        return lon;
    }

}
