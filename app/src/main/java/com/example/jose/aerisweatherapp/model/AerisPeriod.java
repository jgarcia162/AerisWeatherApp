package com.example.jose.aerisweatherapp.model;

/**
 * Created by Joe on 12/22/16.
 */
public class AerisPeriod {
    private int maxTempC;
    private int maxTempF;
    private int minTempC;
    private int minTempF;
    private String dateTimeISO;
    private String icon;

    public int getMaxTempC() {
        return maxTempC;
    }

    public int getMaxTempF() {
        return maxTempF;
    }

    public int getMinTempC() {
        return minTempC;
    }

    public int getMinTempF() {
        return minTempF;
    }

    public String getDateTimeISO() {
        return dateTimeISO;
    }

    public String getIcon() {
        return icon;
    }
}
