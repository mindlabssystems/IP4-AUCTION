package com.xsusenet.ip4.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xsusenet.ip4.Models.Country.Country;
import com.xsusenet.ip4.Models.RegResult;
import com.xsusenet.ip4.Models.Regions.RegionResult;
import com.xsusenet.ip4.R;

import java.util.ArrayList;
import java.util.List;

public class RegionsListAdapter extends ArrayAdapter<RegionResult> {
    private List<RegionResult> dataSet;
    Context mContext;
    int resourceLayout;
    LayoutInflater inflater;


    public void notifyChange(List<RegionResult> regionResults) {
        this.dataSet =  regionResults;
        notifyDataSetChanged();
    }
    private class ViewHolder {

        TextView areaName;
        TextView postalcode;

    }

    public RegionsListAdapter(ArrayList<RegionResult> data, int resourceLayout, Context context) {
        super(context, R.layout.spinner_item, data);
        this.dataSet = data;
        this.mContext = context;
        this.resourceLayout = resourceLayout;
        inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        final RegionsListAdapter.ViewHolder holder;

        if (view == null) {

            holder = new RegionsListAdapter.ViewHolder();
            view = inflater.inflate(R.layout.spinner_item, null);
            holder.areaName = (TextView) view.findViewById(R.id.country_text);

            view.setTag(holder);

        } else {
            holder = (RegionsListAdapter.ViewHolder) view.getTag();
        }

        holder.areaName.setText(dataSet.get(position).getRegionName());

        return view;

    }

}
