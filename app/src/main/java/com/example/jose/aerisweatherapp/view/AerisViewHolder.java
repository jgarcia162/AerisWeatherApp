package com.example.jose.aerisweatherapp.view;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.jose.aerisweatherapp.MainActivity;
import com.example.jose.aerisweatherapp.R;
import com.example.jose.aerisweatherapp.model.AerisPeriod;

public class AerisViewHolder extends RecyclerView.ViewHolder {
    private TextView timeStampTV;
    private TextView minTempTV;
    private TextView maxTempTV;
    private View layout;
    private Context context;
    private Resources resources;



    public AerisViewHolder(View itemView) {
        super(itemView);
        timeStampTV = (TextView) itemView.findViewById(R.id.time_stamp_tv);
        minTempTV = (TextView) itemView.findViewById(R.id.min_temp_tv);
        maxTempTV= (TextView) itemView.findViewById(R.id.max_temp_tv);
        layout = (View) itemView.findViewById(R.id.card_view_layout);
        context = itemView.getContext();
        resources = context.getResources();
    }

    public void bind(AerisPeriod period){
        timeStampTV.setText(period.getDayOfTheWeek());
        if(!MainActivity.isMetricPressed)
            setDecimalTemp(period);

        else
            setMetricTemp(period);

        layout.setBackground(period.getIconDrawable(context));
    }

    private void setMetricTemp(AerisPeriod period){
        maxTempTV.setText(String.format(resources.getString(R.string.details_high_text),period.getMaxTempC()));
        minTempTV.setText(String.format(resources.getString(R.string.details_low_text),period.getMinTempC()));
    }

    private void setDecimalTemp(AerisPeriod period){
        maxTempTV.setText(String.format(resources.getString(R.string.details_high_text), period.getMaxTempF()));
        minTempTV.setText(String.format(resources.getString(R.string.details_low_text), period.getMinTempF()));
    }
}
