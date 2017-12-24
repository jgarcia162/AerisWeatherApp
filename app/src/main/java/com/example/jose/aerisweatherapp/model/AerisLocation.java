package com.example.jose.aerisweatherapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Joe on 12/22/16.
 */
public class AerisLocation {
    private float lat;

    @SerializedName("long")
    private float lon;

    public AerisLocation(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }
}
