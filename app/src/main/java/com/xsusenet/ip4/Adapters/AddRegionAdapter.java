package com.xsusenet.ip4.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.xsusenet.ip4.Models.Regions.RegionResult;
import com.xsusenet.ip4.R;

import java.util.ArrayList;
import java.util.List;

public class AddRegionAdapter extends RecyclerView.Adapter<AddRegionAdapter.ViewHolder>{

    public static List<RegionResult> regionResults = new ArrayList<>();
    Context mContext;
    LayoutInflater inflater;

    RegionsAdapter.OnItemClickListener itemClickListener;

    public AddRegionAdapter(ArrayList<RegionResult> regionResultList, Context context, RegionsAdapter.OnItemClickListener onItemClickListener) {

        regionResults = regionResultList;
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        this.itemClickListener = onItemClickListener;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_item_add, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.text.setText(regionResults.get(position).getRegionName());
        if(regionResults.get(position).isChecked()) {

            String uri = "@drawable/ic_tick_small";  // where myresource (without the extension) is the file

            int imageResource = mContext.getResources().getIdentifier(uri, null, mContext.getPackageName());

            Drawable res = mContext.getResources().getDrawable(imageResource);
            holder.checkbox.setImageDrawable(res);
        }
        else {
            String uri = "@drawable/ic_check_box";  // where myresource (without the extension) is the file

            int imageResource = mContext.getResources().getIdentifier(uri, null, mContext.getPackageName());

            Drawable res = mContext.getResources().getDrawable(imageResource);
            holder.checkbox.setImageDrawable(res);
        }
    }

    @Override
    public int getItemCount() {
        return regionResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        AppCompatImageView checkbox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text = (TextView) itemView.findViewById(R.id.text);
            checkbox = itemView.findViewById(R.id.checkbox);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClicked(getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }
}
