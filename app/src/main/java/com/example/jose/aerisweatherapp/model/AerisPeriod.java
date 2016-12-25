package com.example.jose.aerisweatherapp.model;

/**
 * Created by Joe on 12/22/16.
 */
public class AerisPeriod {
    private int maxTempC;
    private int maxTempF;
    private int minTempC;
    private int minTempF;
    private int humidity;
    private int uvi;
    private int windSpeedMPH;
    private int windSpeedKPH;
    private long sunrise;
    private long sunset;
    private String windDir;
    private String dateTimeISO;
    private String icon;
    private String weather;
    private String weatherPrimary;

    public int getHumidity() {
        return humidity;
    }

    public long getSunrise() {
        return sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public int getUvi() {
        return uvi;
    }

    public String getWeather() {
        return weather;
    }

    public String getWindDir() {
        return windDir;
    }

    public int getWindSpeedKPH() {
        return windSpeedKPH;
    }

    public int getWindSpeedMPH() {
        return windSpeedMPH;
    }

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

    public String getWeatherPrimary() {
        return weatherPrimary;
    }
}
