package com.example.jose.aerisweatherapp.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jose.aerisweatherapp.R;
import com.example.jose.aerisweatherapp.model.AerisPeriod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AerisViewHolder extends RecyclerView.ViewHolder {
    private TextView timeStampTV;
    private TextView minTempTV;
    private TextView maxTempTV;
    private ImageView weatherIconIV;
    private Context context;


    public AerisViewHolder(View itemView) {
        super(itemView);
        timeStampTV = (TextView) itemView.findViewById(R.id.time_stamp_tv);
        minTempTV = (TextView) itemView.findViewById(R.id.min_temp_tv);
        maxTempTV= (TextView) itemView.findViewById(R.id.max_temp_tv);
        weatherIconIV = (ImageView) itemView.findViewById(R.id.icon_iv);
        context = itemView.getContext();
    }

    public void bind(AerisPeriod period){
        timeStampTV.setText(parseDayOfTheWeek(period));
        minTempTV.setText(String.valueOf(period.getMinTempF()));
        maxTempTV.setText(String.valueOf(period.getMaxTempF()));
        weatherIconIV.setImageDrawable(getIcon(period));
    }

    private String parseDayOfTheWeek(AerisPeriod period) {
        String dayOfTheWeek="";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss-HH:mm");
        try {
            Date date = simpleDateFormat.parse(period.getDateTimeISO()); //2016-12-23T07:00:00-05:00
            dayOfTheWeek = new SimpleDateFormat("E").format(date);
            Log.d("DAY OF THE WEEK",dayOfTheWeek);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dayOfTheWeek;
    }

    private Drawable getIcon(AerisPeriod period){
        Resources resources = context.getResources();
        Drawable drawable;
        String uri = "@drawable/"+ period.getIcon();
        uri = uri.replace(".png","");
        int imageResource = resources.getIdentifier(uri, null, context.getPackageName());
        drawable = resources.getDrawable(imageResource,null);
        return drawable;
    }

}
