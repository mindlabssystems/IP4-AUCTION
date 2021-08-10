package com.xsusenet.ip4.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.internal.$Gson$Preconditions;
import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.Models.MyBids.Bid;
import com.xsusenet.ip4.Models.Sales.SalesResult;
import com.xsusenet.ip4.Models.WatchList.Result;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.UI.Buy.BuyActivity;
import com.xsusenet.ip4.UI.BuyAuction.BuyAuctionActivity;
import com.xsusenet.ip4.UI.MyBids.MyBidsActivity;
import com.xsusenet.ip4.UI.Sell.SellActivity;
import com.xsusenet.ip4.UI.SellAuction.SellAuctionActivity;
import com.xsusenet.ip4.Util.Constants;
import com.xsusenet.ip4.Util.Util;

import java.util.List;

import cn.iwgang.countdownview.CountdownView;

public class MyBidsAdapter extends RecyclerView.Adapter<MyBidsAdapter.MyViewHolder> {

    List<Bid> bids;
    Context context;

    int total_amount;
    String sCDT;

    public MyBidsAdapter(MyBidsActivity myBidsActivity, List<Bid> bidList) {
        this.bids = bidList;
        this.context = myBidsActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_bids_item, parent, false);
        return new MyBidsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Bid salesResult = bids.get(position);
        holder.bids.setText(salesResult.getBidAmount());


        StringBuilder title = new StringBuilder();
        String[] strArray = salesResult.getStartingIp().split("\\.");
        for (int j = 0; j < strArray.length; j++) {
            if (j == 0) {
                for (int i = 0; i < strArray[j].length(); i++) {
                    title.append(strArray[j].charAt(i));
                }
            } else {
                for (int i = 0; i < strArray[j].length(); i++) {
                    title.append("X");
                }
            }
            if (j != strArray.length - 1)
                title.append(".");
        }

        String blockS = "/" +salesResult.getBlockSize();
        title.append(blockS);
        title.append(" - ");
        title.append(salesResult.getRegionName());
        holder.title.setText(title);


        holder.openingBid.setText(salesResult.getOpeningBid());


        if (salesResult.getWinLose().equals("1")) {
            holder.status.setText("Bid Won");
            holder.countdownView.setVisibility(View.GONE);
            holder.endsIn.setVisibility(View.VISIBLE);
            holder.endsIn.setText("EXPIRED");
        } else {
            if (salesResult.getSaleStatus().equals("1")) {
                holder.status.setText("Bid ongoing");
//                holder.endsIn.setText("Bid Ongoing");
                holder.countdownView.setVisibility(View.VISIBLE);
                holder.endsIn.setVisibility(View.GONE);

                this.sCDT = MyPreferenceManager.getInstance().getPref("STIME", "");
                String cdt = "";
                if (sCDT != null && !sCDT.isEmpty()) {
                    cdt = sCDT.split(" ")[0].split("-")[1] + "/" + sCDT.split(" ")[0].split("-")[2] + "/" + sCDT.split(" ")[0].split("-")[0] + "  " + sCDT.split(" ")[1];
                } else {
                    cdt = Util.getUtils().getCDT();
                }
                String[] end_d_t = salesResult.getEndDate().split(" ");
                String[] ends = end_d_t[0].split("-");
                String endsdt = ends[1] + "/" + ends[2] + "/" + ends[0] + " 23:59:59";
                long minites = Util.getUtils().differenceInMinit(cdt, endsdt);
                if (minites > 0) {
                    holder.countdownView.start(minites);
                } else {
                    holder.countdownView.stop();
                    holder.countdownView.allShowZero();
                }
            }
            else {
                holder.countdownView.setVisibility(View.GONE);
                holder.endsIn.setVisibility(View.VISIBLE);
                holder.endsIn.setText("EXPIRED");
                holder.status.setText("Lost Bid");
            }
        }
        if (salesResult.getSaleStatus().equals("2")) {
            holder.winningBid.setText(salesResult.getWinningAmount());
            holder.winningLay.setVisibility(View.VISIBLE);
            total_amount = Integer.parseInt(salesResult.getWinningAmount());
        } else {
            holder.winningLay.setVisibility(View.GONE);
            total_amount = Integer.parseInt(salesResult.getOpeningBid());
        }


        float no_of_address = Float.parseFloat(salesResult.getNoOfAddress());
        double price_per_add = total_amount / no_of_address;
        String s = "$" + round(price_per_add, 2);
        holder.pricePerAddress.setText(s);

        if (salesResult.getTransferrableTo() != null) {
            if (salesResult.getTransferrableTo().size() > 0) {
                int size = salesResult.getTransferrableTo().size();
                StringBuilder builder = new StringBuilder();
                builder.append("Transferable to: ");
                for (int i = 0; i < size; i++) {
                    builder.append(salesResult.getTransferrableTo().get(i).getRegionName());
                    if (i < size - 1)
                        builder.append(", ");
                }
                holder.transferableTx.setText(builder);
                holder.transferableTx.setVisibility(View.VISIBLE);
                holder.includedLay.setVisibility(View.GONE);

            }
        } else {
            holder.transferableTx.setVisibility(View.GONE);

//            holder.transferableTx.setText("Transferable to: ");
        }

        if (salesResult.getSaleOrRent().equals("1")) {
            holder.includedLay.setVisibility(View.GONE);
        }
        else {
            holder.includedLay.setVisibility(View.VISIBLE);

        }
        holder.toDetailLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (salesResult.getSaleOrRent().equals("1")) {
                    if (salesResult.getSaleMethod().equals("1")) {
                        Intent intent = new Intent(context, BuyAuctionActivity.class);
                        intent.putExtra("SALE_TYPE", Constants.PURCHASE);
                        intent.putExtra("SALE_METHOD", Constants.AUCTION);
                        intent.putExtra("list_id", String.valueOf(salesResult.getListId()));
                        context.startActivity(intent);
                    }
                } else {
                    if (salesResult.getSaleMethod().equals("1")) {
                        Intent intent = new Intent(context, BuyAuctionActivity.class);
                        intent.putExtra("SALE_TYPE", Constants.RENT);
                        intent.putExtra("SALE_METHOD", Constants.AUCTION);
                        intent.putExtra("list_id", String.valueOf(salesResult.getListId()));
                        context.startActivity(intent);
                    }
                }
            }

        });
    }
    @Override
    public void onViewAttachedToWindow(MyBidsAdapter.MyViewHolder holder) {

        int pos = holder.getAdapterPosition();
//            Log.d("MyViewHolder", String.format("mCvCountdownView %s is attachedToWindow", pos));

        Bid salesResult = bids.get(pos);


        if (salesResult.getWinLose().equals("1")) {
            holder.status.setText("Bid Won");
            holder.countdownView.setVisibility(View.GONE);
            holder.endsIn.setVisibility(View.VISIBLE);
            holder.endsIn.setText("EXPIRED");
        } else {
            if (salesResult.getSaleStatus().equals("1")) {
                holder.status.setText("Bid ongoing");
//                holder.endsIn.setText("Bid Ongoing");
                holder.countdownView.setVisibility(View.VISIBLE);
                holder.endsIn.setVisibility(View.GONE);

                this.sCDT = MyPreferenceManager.getInstance().getPref("STIME", "");
                String cdt = "";
                if (sCDT != null && !sCDT.isEmpty()) {
                    cdt = sCDT.split(" ")[0].split("-")[1] + "/" + sCDT.split(" ")[0].split("-")[2] + "/" + sCDT.split(" ")[0].split("-")[0] + "  " + sCDT.split(" ")[1];
                } else {
                    cdt = Util.getUtils().getCDT();
                }
                String[] end_d_t = salesResult.getEndDate().split(" ");
                String[] ends = end_d_t[0].split("-");
                String endsdt = ends[1] + "/" + ends[2] + "/" + ends[0] + " 23:59:59";
                long minites = Util.getUtils().differenceInMinit(cdt, endsdt);
                if (minites > 0) {
                    holder.countdownView.start(minites);
                } else {
                    holder.countdownView.stop();
                    holder.countdownView.allShowZero();
                }
            }
            else {
                holder.countdownView.setVisibility(View.GONE);
                holder.endsIn.setVisibility(View.VISIBLE);
                holder.endsIn.setText("EXPIRED");
                holder.status.setText("Lost Bid");
            }
        }



    }

    @Override
    public int getItemCount() {
        return bids.size();
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView openingBid;
        TextView title;
        TextView transferableTx;
        TextView openingBidTitle;
        TextView pricePerAddressTx;
        TextView pricePerAddress;
        TextView endsInTitle;
        TextView endsIn;
        TextView bidsTitle;
        TextView bids;
        TextView status, winningBid;
        AppCompatImageView gotoDetail;

        ConstraintLayout winningLay;
        CountdownView countdownView;
        RelativeLayout toDetailLay;
        RelativeLayout includedLay;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            openingBid = itemView.findViewById(R.id.opening_bid);
            endsIn = itemView.findViewById(R.id.ends_in_date);
            status = itemView.findViewById(R.id.status);
            transferableTx = itemView.findViewById(R.id.transferable_tx);
            openingBidTitle = itemView.findViewById(R.id.opening_bid_title);
            bidsTitle = itemView.findViewById(R.id.bids_title);
            winningBid = itemView.findViewById(R.id.winning_bid);
            bids = itemView.findViewById(R.id.bids);
            title = itemView.findViewById(R.id.title);
            gotoDetail = itemView.findViewById(R.id.go_to_detail);
            pricePerAddress = itemView.findViewById(R.id.price_per_address);
            winningLay = itemView.findViewById(R.id.constraint_3);
            countdownView = itemView.findViewById(R.id.ends_in);
            toDetailLay = itemView.findViewById(R.id.to_detail_lay);
            includedLay = itemView.findViewById(R.id.included_lay);

            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.title));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.opening_bid));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.price_per_address));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.ends_in));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.ends_in_date));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.bids));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.winning_bid));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.status));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.mini_term));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.status));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.transferable_tx));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.opening_bid_title));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.winning_bid_title));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.price_per_address_tx));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.ends_in_title));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.bids_title));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.min_term_title));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.included_tx));

        }
    }
}
