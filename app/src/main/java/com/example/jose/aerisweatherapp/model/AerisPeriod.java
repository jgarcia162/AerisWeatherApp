package com.example.jose.aerisweatherapp.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Joe on 12/22/16.
 */
public class AerisPeriod implements Parcelable {
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

    protected AerisPeriod(Parcel in) {
        maxTempC = in.readInt();
        maxTempF = in.readInt();
        minTempC = in.readInt();
        minTempF = in.readInt();
        humidity = in.readInt();
        uvi = in.readInt();
        windSpeedMPH = in.readInt();
        windSpeedKPH = in.readInt();
        sunrise = in.readLong();
        sunset = in.readLong();
        windDir = in.readString();
        dateTimeISO = in.readString();
        icon = in.readString();
        weather = in.readString();
        weatherPrimary = in.readString();
    }

    public static final Creator<AerisPeriod> CREATOR = new Creator<AerisPeriod>() {
        @Override
        public AerisPeriod createFromParcel(Parcel in) {
            return new AerisPeriod(in);
        }

        @Override
        public AerisPeriod[] newArray(int size) {
            return new AerisPeriod[size];
        }
    };

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
    public String getDayOfTheWeek() {
        String dayOfTheWeek="";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss-HH:mm");
        try {
            Date date = simpleDateFormat.parse(this.dateTimeISO); //2016-12-23T07:00:00-05:00
            dayOfTheWeek = new SimpleDateFormat("E").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dayOfTheWeek;
    }
    public String getFullDayName(){
        switch(getDayOfTheWeek()){
            case "Mon":
                return "Monday";
            case "Tue":
                return "Tuesday";
            case "Wed":
                return "Wednesday";
            case "Thu":
                return "Thursday";
            case "Fri":
                return "Friday";
            case "Sat":
                return "Saturday";
            case "Sun":
                return "Sunday";
        }
        return "Today";
    }

    public String getIcon() {

        return icon;
    }
    public Drawable getIconDrawable(Context context){
        Resources resources = context.getResources();
        Drawable drawable;
        String uri = "@drawable/"+ this.icon;
        uri = uri.replace(".png","");
        int imageResource = resources.getIdentifier(uri, null, context.getPackageName());
        drawable = resources.getDrawable(imageResource,null);
        return drawable;
    }

    public String getWeatherPrimary() {
        return weatherPrimary;
    }

    public String getSunriseTimeString(){
        Date date = new Date(getSunrise());
        DateFormat formatter = new SimpleDateFormat("hh:mm");
        return formatter.format(date);

    }
    public String getSunriseMilitaryTimeString() {
        Date date = new Date(getSunrise());
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(date);
    }
    public String getSunsetTimeString(){
        Date date = new Date(getSunset());
        DateFormat formatter = new SimpleDateFormat("hh:mm");
        return formatter.format(date);
    }
    public String getSunsetMilitaryTimeString() {
        Date date = new Date(getSunset());
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(date);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(maxTempC);
        parcel.writeInt(maxTempF);
        parcel.writeInt(minTempC);
        parcel.writeInt(minTempF);
        parcel.writeInt(humidity);
        parcel.writeInt(uvi);
        parcel.writeInt(windSpeedMPH);
        parcel.writeInt(windSpeedKPH);
        parcel.writeLong(sunrise);
        parcel.writeLong(sunset);
        parcel.writeString(windDir);
        parcel.writeString(dateTimeISO);
        parcel.writeString(icon);
        parcel.writeString(weather);
        parcel.writeString(weatherPrimary);
    }


}
