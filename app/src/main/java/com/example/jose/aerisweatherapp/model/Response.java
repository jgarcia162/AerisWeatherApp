package com.example.jose.aerisweatherapp.model;

import java.util.List;

public class Response {
    private String id;
    private String interval;
    private AerisLocation loc;
    private List<AerisPeriod> periods;

    public String getId() {
        return id;
    }

    public String getInterval() {
        return interval;
    }

    public AerisLocation getLocation() {
        return loc;
    }

    public List<AerisPeriod> getPeriod() {
        return periods;
    }
}
