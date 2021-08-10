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
import com.xsusenet.ip4.R;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter  extends ArrayAdapter<Country> {

    private List<Country> dataSet;
    Context mContext;
    int resourceLayout;
    LayoutInflater inflater;


    public void notifyChange(List<Country> countryList) {
        this.dataSet =  countryList;
        notifyDataSetChanged();
    }
    private class ViewHolder {

        TextView areaName;
        TextView postalcode;

    }

    public CountryAdapter(ArrayList<Country> data,int resourceLayout, Context context) {
        super(context, R.layout.spinner_item, data);
        this.dataSet = data;
        this.mContext = context;
        this.resourceLayout = resourceLayout;
        inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        final ViewHolder holder;

        if (view == null) {

            holder = new ViewHolder();
            view = inflater.inflate(R.layout.spinner_item, null);
            holder.areaName = (TextView) view.findViewById(R.id.country_text);

            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.areaName.setText(dataSet.get(position).getCountriesName());

        return view;

    }

}
