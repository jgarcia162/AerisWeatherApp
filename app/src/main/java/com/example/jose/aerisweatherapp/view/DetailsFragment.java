package com.example.jose.aerisweatherapp.view;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jose.aerisweatherapp.MainActivity;
import com.example.jose.aerisweatherapp.R;
import com.example.jose.aerisweatherapp.model.AerisPeriod;


public class DetailsFragment extends Fragment {
    private TextView dayOfTheWeekTV;
    private TextView weatherTV;
    private TextView maxTempTV;
    private TextView minTempTV;
    private TextView sunriseTV;
    private TextView sunsetTV;
    private TextView weatherDescriptionTV;
    private TextView humidityTV;
    private TextView windSpeedTV;
    private TextView windDirectionTV;
    private TextView uviTV;
    private ImageView iconIV;
    private View rootView;
    private Bundle bundles;
    private AerisPeriod data;
    private Resources resources;

    public DetailsFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get bundles here
        bundles = getArguments();
        data = bundles.getParcelable("data");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.details_fragment_layout,container,false);
        resources = getResources();
        initializeViews(rootView);
        bindDataToViews(data);
        return rootView;
    }

    public void initializeViews(View view){
        dayOfTheWeekTV = (TextView) view.findViewById(R.id.day_of_the_week_tv);
        weatherTV = (TextView) view.findViewById(R.id.weather_details_tv);
        maxTempTV = (TextView) view.findViewById(R.id.detail_max_temp_tv);
        minTempTV = (TextView) view.findViewById(R.id.detail_min_temp_tv);
        sunriseTV = (TextView) view.findViewById(R.id.sunrise_tv);
        sunsetTV = (TextView) view.findViewById(R.id.sunset_tv);
        weatherDescriptionTV = (TextView) view.findViewById(R.id.weather_description_tv);
        humidityTV = (TextView) view.findViewById(R.id.humidity_tv);
        windSpeedTV = (TextView) view.findViewById(R.id.wind_speed_tv);
        windDirectionTV = (TextView) view.findViewById(R.id.wind_direction_tv);
        uviTV = (TextView) view.findViewById(R.id.uvi_tv);
        iconIV = (ImageView) view.findViewById(R.id.details_icon_iv);
    }


    public void bindDataToViews(AerisPeriod period){
        dayOfTheWeekTV.setText(period.getFullDayName());
        weatherTV.setText(period.getWeatherPrimary());
        if(MainActivity.isMetricPressed)
            setMetricSystem(period);
        else
            setDecimalSystem(period);
        weatherDescriptionTV.setText(period.getWeather());
        humidityTV.setText(String.format(resources.getString(R.string.humidity_text),period.getHumidity()));
        windDirectionTV.setText(String.format(resources.getString(R.string.wind_direction_text),period.getWindDir()));
        uviTV.setText(String.format(resources.getString(R.string.uvi_text),period.getUvi()));
        iconIV.setImageDrawable(period.getIconDrawable(getContext()));
    }

    private void setDecimalSystem(AerisPeriod period) {
        maxTempTV.setText(String.format(resources.getString(R.string.details_high_text),period.getMaxTempF()));
        minTempTV.setText(String.format(resources.getString(R.string.details_low_text),period.getMinTempF()));
        sunriseTV.setText(period.getSunriseTimeString());
        sunsetTV.setText(period.getSunsetTimeString());
        windSpeedTV.setText(String.format(resources.getString(R.string.wind_speed_text),period.getWindSpeedMPH()));
    }

    private void setMetricSystem(AerisPeriod period){
        maxTempTV.setText(String.format(resources.getString(R.string.details_high_text),period.getMaxTempC()));
        minTempTV.setText(String.format(resources.getString(R.string.details_low_text),period.getMinTempC()));
        sunriseTV.setText(period.getSunriseMilitaryTimeString());
        sunsetTV.setText(period.getSunsetMilitaryTimeString());
        windSpeedTV.setText(String.format(resources.getString(R.string.wind_speed_text),period.getWindSpeedKPH()));
    }

    public void refreshViews(){
        bindDataToViews(data);

    }


}
