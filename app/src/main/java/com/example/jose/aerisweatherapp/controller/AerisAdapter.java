package com.example.jose.aerisweatherapp.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jose.aerisweatherapp.R;
import com.example.jose.aerisweatherapp.model.AerisPeriod;
import com.example.jose.aerisweatherapp.view.AerisViewHolder;

import java.util.List;

/**
 * Created by Joe on 12/23/16.
 */
public class AerisAdapter extends RecyclerView.Adapter<AerisViewHolder>{
    List<AerisPeriod> dataSet;

    public AerisAdapter(List<AerisPeriod> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public AerisViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aeris_item_layout,parent,false);
        return new AerisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AerisViewHolder holder, int position) {
        holder.bind(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void setData(List<AerisPeriod> newData){
        this.dataSet = newData;
    }
}
