package com.example.jose.aerisweatherapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jose.aerisweatherapp.R;


public class DetailsFragment extends Fragment {
    private TextView dayOfTheWeekTV;
    private TextView weatherTV;
    private TextView maxTempTV;
    private TextView minTempTV;
    private TextView sunriseTV;
    private TextView sunsetTV;
    private TextView weatherDetailsTV;
    private TextView humidityTV;
    private TextView windSpeedTV;
    private TextView windDirectionTV;
    private TextView uviTV;
    private ImageView iconIV;
    private View rootView;

    public DetailsFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.details_fragment_layout,container,false);
        return rootView;
    }

    private String getFullDayName(String dayOfTheWeek){
        switch(dayOfTheWeek){
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
}
