package com.example.jose.aerisweatherapp.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jose.aerisweatherapp.MainActivity;
import com.example.jose.aerisweatherapp.R;
import com.example.jose.aerisweatherapp.model.AerisPeriod;

public class AerisViewHolder extends RecyclerView.ViewHolder {
    private TextView timeStampTV;
    private TextView minTempTV;
    private TextView maxTempTV;
    private ImageView weatherIV;
    private View layout;
    private Context context;
    private Resources resources;
    private FragmentManager fragmentManager;
    private AerisPeriod data;

    public AerisViewHolder(View itemView) {
        super(itemView);
        timeStampTV = itemView.findViewById(R.id.time_stamp_tv);
        minTempTV =  itemView.findViewById(R.id.min_temp_tv);
        maxTempTV= itemView.findViewById(R.id.max_temp_tv);
        weatherIV = itemView.findViewById(R.id.weather_imageview);
        layout = itemView.findViewById(R.id.card_view_layout);
        context = itemView.getContext();
        resources = context.getResources();
        fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
    }

    public void bind(AerisPeriod period){
        this.data = period;
        timeStampTV.setText(period.getDayOfTheWeek());
        if(!MainActivity.isMetricPressed)
            setDecimalTemp(period);
        else
            setMetricTemp(period);

        weatherIV.setImageDrawable(period.getIconDrawable(context));
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putParcelable("data", data);
                DetailsFragment fragment = new DetailsFragment();
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.details_fragment_container, fragment, "clicked_fragment");
                fragmentTransaction.commit();
            }
        });
    }

    private void setMetricTemp(AerisPeriod period){
        maxTempTV.setText(String.format(resources.getString(R.string.details_high_text),period.maxTempC()));
        minTempTV.setText(String.format(resources.getString(R.string.details_low_text),period.minTempC()));
    }

    private void setDecimalTemp(AerisPeriod period){
        maxTempTV.setText(String.format(resources.getString(R.string.details_high_text), period.maxTempF()));
        minTempTV.setText(String.format(resources.getString(R.string.details_low_text), period.minTempF()));
    }
}
