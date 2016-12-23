package com.example.jose.aerisweatherapp.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.jose.aerisweatherapp.R;
import com.example.jose.aerisweatherapp.model.AerisPeriod;

/**
 * Created by Joe on 12/23/16.
 */

public class AerisViewHolder extends RecyclerView.ViewHolder {
    private TextView timeStampTV;
    private TextView minTempTV;
    private TextView maxTempTV;


    public AerisViewHolder(View itemView) {
        super(itemView);
        timeStampTV = (TextView) itemView.findViewById(R.id.time_stamp_tv);
        minTempTV = (TextView) itemView.findViewById(R.id.min_temp_tv);
        maxTempTV= (TextView) itemView.findViewById(R.id.max_temp_tv);
    }

    public void bind(AerisPeriod period){
        timeStampTV.setText(period.getDateTimeISO());
        minTempTV.setText(String.valueOf(period.getMinTempF()));
        maxTempTV.setText(String.valueOf(period.getMaxTempF()));
    }
}
