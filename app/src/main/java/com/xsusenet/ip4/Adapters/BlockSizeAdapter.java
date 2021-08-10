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
import com.xsusenet.ip4.Models.Country.Country;
import com.xsusenet.ip4.R;

import java.util.ArrayList;
import java.util.List;

public class BlockSizeAdapter extends RecyclerView.Adapter<BlockSizeAdapter.ViewHolder> {
    public static List<Size> sizeList = new ArrayList<>();
    Context mContext;
    int resourceLayout;
    LayoutInflater inflater;
    OnItemClickListener itemClickListener;

    public BlockSizeAdapter(ArrayList<Size> data, Context context,OnItemClickListener onItemClickListener) {
        sizeList = data;
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        this.itemClickListener = onItemClickListener;
    }

/*
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        final BlockSizeAdapter.ViewHolder holder;
        Size size = sizeList.get(position);
        if (view == null) {

            holder = new BlockSizeAdapter.ViewHolder();
            view = inflater.inflate(R.layout.spinner_checkbox_item, null);
            holder.text = (TextView) view.findViewById(R.id.text);
            holder.checkbox = view.findViewById(R.id.checkbox);

            if(size.isChecked()){

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
            holder.checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if( !size.isChecked()){
                        size.setChecked(true);

                        Log.d("TAG","position"+position);
                        String uri = "@drawable/ic_tick";  // where myresource (without the extension) is the file

                        int imageResource = mContext.getResources().getIdentifier(uri, null, mContext.getPackageName());

                        Drawable res = mContext.getResources().getDrawable(imageResource);
                        holder.checkbox.setImageDrawable(res);
                    }
                    else {
                        size.setChecked(false);

                        Log.d("TAG","position"+position);
                        String uri = "@drawable/ic_green_check_box";  // where myresource (without the extension) is the file

                        int imageResource = mContext.getResources().getIdentifier(uri, null, mContext.getPackageName());

                        Drawable res = mContext.getResources().getDrawable(imageResource);
                        holder.checkbox.setImageDrawable(res);
                    }
                }
            });
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            view.setTag(holder);

        } else {
            holder = (BlockSizeAdapter.ViewHolder) view.getTag();
        }

        holder.text.setText(sizeList.get(position).getBlockSize());

        return view;

    }
*/

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_checkbox_item, parent, false);
        return new BlockSizeAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.text.setText(sizeList.get(position).getBlockSize());
        if (sizeList.get(position).isChecked()) {
            Log.d("TAG", "position" + position);
            String uri = "@drawable/ic_tick";  // where myresource (without the extension) is the file

            int imageResource = mContext.getResources().getIdentifier(uri, null, mContext.getPackageName());

            Drawable res = mContext.getResources().getDrawable(imageResource);
            holder.checkbox.setImageDrawable(res);
        } else {
            Log.d("TAG", "position" + position);
            String uri = "@drawable/ic_green_check_box";  // where myresource (without the extension) is the file

            int imageResource = mContext.getResources().getIdentifier(uri, null, mContext.getPackageName());

            Drawable res = mContext.getResources().getDrawable(imageResource);
            holder.checkbox.setImageDrawable(res);
        }
/*
        holder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sizeList.get(position).isChecked()) {
//                    sizeList.get(position).setChecked(true);
                    Log.d("TAG", "position" + position);
                    String uri = "@drawable/ic_tick";  // where myresource (without the extension) is the file

                    int imageResource = mContext.getResources().getIdentifier(uri, null, mContext.getPackageName());

                    Drawable res = mContext.getResources().getDrawable(imageResource);
                    holder.checkbox.setImageDrawable(res);
                } else {
//                    sizeList.get(position).setChecked(false);
                    Log.d("TAG", "position" + position);
                    String uri = "@drawable/ic_green_check_box";  // where myresource (without the extension) is the file

                    int imageResource = mContext.getResources().getIdentifier(uri, null, mContext.getPackageName());

                    Drawable res = mContext.getResources().getDrawable(imageResource);
                    holder.checkbox.setImageDrawable(res);
                }
//                notifyDataSetChanged();

            }
        });
*/

    }

    @Override
    public int getItemCount() {
        return sizeList.size();
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
