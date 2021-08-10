package com.xsusenet.ip4.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xsusenet.ip4.Models.SortBy.ModelSortBy;
import com.xsusenet.ip4.R;

import java.util.ArrayList;
import java.util.List;

public class SortByAdapter extends RecyclerView.Adapter<SortByAdapter.ViewHolder> {
    public static List<ModelSortBy> modelSortByList = new ArrayList<>();
    Context mContext;
    int resourceLayout;
    LayoutInflater inflater;

    private int selectedPosition = -1;

    public SortByAdapter(ArrayList<ModelSortBy> data, Context context) {

        modelSortByList = data;
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_item_radio, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.textView.setText(modelSortByList.get(i).getValue());

        holder.radioButton.setChecked(i == selectedPosition);

        //Set the position tag to both radio button and label
        holder.radioButton.setTag(i);

        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCheckChanged(v);
            }
        });
    }

    private void itemCheckChanged(View v) {
        selectedPosition = (Integer) v.getTag();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return modelSortByList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        RadioButton radioButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.checkbox);
            textView = itemView.findViewById(R.id.text);
        }
    }

    //Return the selectedPosition item
    public ModelSortBy getSelectedItem() {
        if (selectedPosition != -1) {
            return modelSortByList.get(selectedPosition);
        }
        return modelSortByList.get(0);
    }
}