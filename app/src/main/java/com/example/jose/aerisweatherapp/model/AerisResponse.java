package com.example.jose.aerisweatherapp.model;

import java.util.List;


public class AerisResponse {
    boolean success;
    private String error;
    private List<Response> response;

    public String getError() {
        return error;
    }

    public List<Response> getResponse() {
        return response;
    }



}
