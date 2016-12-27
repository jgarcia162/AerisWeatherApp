package com.example.jose.aerisweatherapp.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jose.aerisweatherapp.R;
import com.example.jose.aerisweatherapp.model.AerisPeriod;

public class AerisViewHolder extends RecyclerView.ViewHolder {
    private TextView timeStampTV;
    private TextView minTempTV;
    private TextView maxTempTV;
    private ImageView iconIV;
    private Context context;


    public AerisViewHolder(View itemView) {
        super(itemView);
        timeStampTV = (TextView) itemView.findViewById(R.id.time_stamp_tv);
        minTempTV = (TextView) itemView.findViewById(R.id.min_temp_tv);
        maxTempTV= (TextView) itemView.findViewById(R.id.max_temp_tv);
        iconIV = (ImageView) itemView.findViewById(R.id.icon_iv);
        context = itemView.getContext();
    }

    public void bind(AerisPeriod period){
        timeStampTV.setText(period.getDayOfTheWeek());
        minTempTV.setText(String.valueOf(period.getMinTempF()));
        maxTempTV.setText(String.valueOf(period.getMaxTempF()));
        iconIV.setImageDrawable(period.getIconDrawable(context));
    }
}
