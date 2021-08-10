package com.xsusenet.ip4.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.xsusenet.ip4.Models.BlockSize.Size;
import com.xsusenet.ip4.Models.Regions.RegionResult;
import com.xsusenet.ip4.R;

import java.util.ArrayList;
import java.util.List;

public class RegionsAdapter  extends RecyclerView.Adapter<RegionsAdapter.ViewHolder> {

    public static List<RegionResult> regionResults = new ArrayList<>();
    Context mContext;
    LayoutInflater inflater;

    OnItemClickListener itemClickListener;
    public RegionsAdapter(ArrayList<RegionResult> regionResultList, Context context, OnItemClickListener onItemClickListener) {

        regionResults = regionResultList;
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        this.itemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_checkbox_item, parent, false);
        return new RegionsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.text.setText(regionResults.get(position).getRegionName());
        if(regionResults.get(position).isChecked()) {

            String uri = "@drawable/ic_tick";  // where myresource (without the extension) is the file

            int imageResource = mContext.getResources().getIdentifier(uri, null, mContext.getPackageName());

            Drawable res = mContext.getResources().getDrawable(imageResource);
            holder.checkbox.setImageDrawable(res);
        }
        else {
            String uri = "@drawable/ic_green_check_box";  // where myresource (without the extension) is the file

            int imageResource = mContext.getResources().getIdentifier(uri, null, mContext.getPackageName());

            Drawable res = mContext.getResources().getDrawable(imageResource);
            holder.checkbox.setImageDrawable(res);
        }

    }

    @Override
    public int getItemCount() {
        return regionResults.size();
    }

    /*
        @NonNull
        @Override
        public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

            final RegionsAdapter.ViewHolder holder;
            RegionResult region = regionResults.get(position);
            if (view == null) {

                holder = new RegionsAdapter.ViewHolder();
                view = inflater.inflate(R.layout.spinner_checkbox_item, null);
                holder.text = (TextView) view.findViewById(R.id.text);
                holder.checkbox = view.findViewById(R.id.checkbox);
                if(region.isChecked()){

                    String uri = "@drawable/ic_tick";  // where myresource (without the extension) is the file

                    int imageResource = mContext.getResources().getIdentifier(uri, null, mContext.getPackageName());

                    Drawable res = mContext.getResources().getDrawable(imageResource);
                    holder.checkbox.setImageDrawable(res);
                }
                else {

                    String uri = "@drawable/ic_green_check_box";  // where myresource (without the extension) is the file

                    int imageResource = mContext.getResources().getIdentifier(uri, null, mContext.getPackageName());

                    Drawable res = mContext.getResources().getDrawable(imageResource);
                    holder.checkbox.setImageDrawable(res);
                }
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if( !region.isChecked()){
                            region.setChecked(true);
                            notifyDataSetChanged();
                            String uri = "@drawable/ic_tick";  // where myresource (without the extension) is the file

                            int imageResource = mContext.getResources().getIdentifier(uri, null, mContext.getPackageName());

                            Drawable res = mContext.getResources().getDrawable(imageResource);
                            holder.checkbox.setImageDrawable(res);
                        }
                        else {
                            region.setChecked(false);
                            notifyDataSetChanged();
                            String uri = "@drawable/ic_green_check_box";  // where myresource (without the extension) is the file

                            int imageResource = mContext.getResources().getIdentifier(uri, null, mContext.getPackageName());

                            Drawable res = mContext.getResources().getDrawable(imageResource);
                            holder.checkbox.setImageDrawable(res);
                        }
                    }
                });
                view.setTag(holder);

            } else {
                holder = (RegionsAdapter.ViewHolder) view.getTag();
            }

            holder.text.setText(regionResults.get(position).getRegionName());

            return view;

        }
    */


    public  class ViewHolder extends RecyclerView.ViewHolder {
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
