package com.example.jose.aerisweatherapp.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Joe on 12/22/16.
 */

@AutoValue
public abstract class AerisPeriod implements Parcelable {
     public abstract int maxTempC();
     public abstract int maxTempF();
     public abstract int minTempC();
     public abstract int minTempF();
     public abstract int humidity();
     public abstract int uvi();
     public abstract int windSpeedMPH();
     public abstract int windSpeedKPH();
     public abstract long sunrise();
     public abstract long sunset();
     public abstract String windDir();
     public abstract String dateTimeISO();
     public abstract String icon();
     public abstract String weather();
     public abstract String weatherPrimary();

    public AerisPeriod() {
    }

    public static AerisPeriod create(int maxTempC, int maxTempF, int minTempC, int minTempF, int humidity, int uvi, int windSpeedMPH, int windSpeedKPH, long sunrise, long sunset, String windDir, String dateTimeISO, String icon, String weather, String weatherPrimary){
       return new AutoValue_AerisPeriod(maxTempC,maxTempF,minTempC,minTempF,humidity,uvi,windSpeedMPH,windSpeedKPH,sunrise,sunset,windDir,dateTimeISO,icon,weather,weatherPrimary);
    }

    public static TypeAdapter<AerisPeriod> typeAdapter(Gson gson){
        return new AutoValue_AerisPeriod.GsonTypeAdapter(gson);
    }

    public String getDayOfTheWeek() {
        String dayOfTheWeek="";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss-HH:mm");
        try {
            Date date = simpleDateFormat.parse(dateTimeISO()); //2016-12-23T07:00:00-05:00
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

    public Drawable getIconDrawable(Context context){
        Resources resources = context.getResources();
        Drawable drawable;
        String uri = "@drawable/"+ icon();
        uri = uri.replace(".png","");
        int imageResource = resources.getIdentifier(uri, null, context.getPackageName());
        drawable = resources.getDrawable(imageResource,null);
        return drawable;
    }


    public String getSunriseTimeString(){
        Date date = new Date(sunrise());
        DateFormat formatter = new SimpleDateFormat("hh:mm");
        return formatter.format(date);

    }
    public String getSunriseMilitaryTimeString() {
        Date date = new Date(sunrise());
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(date);
    }
    public String getSunsetTimeString(){
        Date date = new Date(sunset());
        DateFormat formatter = new SimpleDateFormat("hh:mm");
        return formatter.format(date);
    }
    public String getSunsetMilitaryTimeString() {
        Date date = new Date(sunset());
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(date);
    }

//    @GsonTypeAdapterFactory
//    public abstract class GsonTypeAdapter implements TypeAdapter{
//
//    }

}
