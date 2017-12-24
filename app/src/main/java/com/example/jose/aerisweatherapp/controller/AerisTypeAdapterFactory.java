package com.example.jose.aerisweatherapp.controller;

import com.example.jose.aerisweatherapp.controller.AutoValueGson_AerisTypeAdapterFactory;
import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * Created by Joe on 12/23/17.
 */

@GsonTypeAdapterFactory
public abstract class AerisTypeAdapterFactory implements TypeAdapterFactory{

    public static TypeAdapterFactory create(){
        return new AutoValueGson_AerisTypeAdapterFactory();
    }
}
