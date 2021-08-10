package com.xsusenet.ip4.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.xsusenet.ip4.Models.Bids.Bid;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.UI.Bids.BidListActivity;
import com.xsusenet.ip4.Util.Util;

import java.util.FormatFlagsConversionMismatchException;
import java.util.List;

public class BidsListAdapter extends RecyclerView.Adapter<BidsListAdapter.ViewHolder> {
    Context context;
    List<Bid> bidList;

    public BidsListAdapter(BidListActivity bidListActivity, List<Bid> bidList) {
        this.context = bidListActivity;
        this.bidList = bidList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bid_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bid bid = bidList.get(position);
        if (bid.getWinLose().equals("1")) {
//
            holder.trophy.setVisibility(View.VISIBLE);
        }
        else {
            holder.trophy.setVisibility(View.GONE);

        }
        StringBuilder sb = new StringBuilder();
        String nameArray[] = bid.getName().split(" ");
        for (String name :
                nameArray) {
            for (int i = 0; i < name.length(); i++) {
                if (i == 0) {
                    sb.append(name.charAt(0));
                } else if (i == 1) {
                    sb.append(name.charAt(1));
                } else {
                    sb.append("X");
                }
            }
            sb.append(" ");
        }

        holder.name.setText(sb);

//        holder.addedDate.setText(bid.getCreatedAt());
        String[] dates = bid.getCreatedAt().split("T");
        String atS = dates[1].split("\\.")[0];
        String dateS = dates[0] + " at " + atS;
        holder.addedDate.setText(dateS);

        String bidS = "$" + bid.getBidAmount();
        holder.bidAmount.setText(bidS);
    }

    @Override
    public int getItemCount() {
        return bidList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView nameTitle;
        TextView name;
        TextView bidAmountTitle;
        TextView bidAmount;
        TextView addedDateTitle;
        TextView addedDate;
        AppCompatImageView trophy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTitle = itemView.findViewById(R.id.name_title);
            name = itemView.findViewById(R.id.name);
            bidAmount = itemView.findViewById(R.id.bid_amount);
            bidAmountTitle = itemView.findViewById(R.id.bid_amount_title);
            addedDate = itemView.findViewById(R.id.added_date);
            addedDateTitle = itemView.findViewById(R.id.added_date_title);
            trophy = itemView.findViewById(R.id.trophy);

            Util.getUtils().overrideFontsBold(context, addedDate);
            Util.getUtils().overrideFontsBold(context, bidAmount);
            Util.getUtils().overrideFontsBold(context, name);

            Util.getUtils().overrideFontsRegular(context, addedDateTitle);
            Util.getUtils().overrideFontsRegular(context, bidAmountTitle);
            Util.getUtils().overrideFontsRegular(context, nameTitle);

        }
    }
}
