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
import androidx.recyclerview.widget.RecyclerView;
import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.Models.Sales.SalesResult;
import com.xsusenet.ip4.Models.WatchList.Result;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.UI.Buy.BuyActivity;
import com.xsusenet.ip4.UI.BuyAuction.BuyAuctionActivity;
import com.xsusenet.ip4.UI.Sell.SellActivity;
import com.xsusenet.ip4.UI.SellAuction.SellAuctionActivity;
import com.xsusenet.ip4.Util.Constants;
import com.xsusenet.ip4.Util.Util;
import java.util.List;

public class WatchListAdapter extends RecyclerView.Adapter<WatchListAdapter.MyViewHolder> {

    Context context;
    List<Result> watchList;
    int total_amount;

    public WatchListAdapter(Context jobsFragment, List<Result> watchList) {
        this.context = jobsFragment;
        this.watchList = watchList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_item, parent, false);
        return new WatchListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WatchListAdapter.MyViewHolder holder, int position) {
        Result salesResult = watchList.get(position);
        if(salesResult.getSaleMethod().equals("1") )
        holder.endsIn.setText(salesResult.getEndDate().split(" ")[0]);
        else
            holder.endsIn.setText("No Expiry");
        holder.bids.setText(salesResult.getNoOfBids());
        holder.minTerm.setText(salesResult.getRentMinTerm());
//        String str = salesResult.getStartingIp().split("\\.")[0];
//        String blockS = ".XXX.XX.XX/" + salesResult.getBlockSize();
//        StringBuilder title = new StringBuilder();
//        title.append(str);


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
//        StringBuilder title = new StringBuilder();
//        title.append("/");
//        title.append(salesResult.getBlockSize());
//        title.append(" Block registered in ");
//        title.append(salesResult.getRegionName());
        holder.title.setText(title);

//        if (salesResult.getSaleMethod().equals("1"))
//            total_amount = Integer.parseInt(salesResult.getOpeningBid());
//        else
//            total_amount = Integer.parseInt(salesResult.getSalePrice());


        if (salesResult.getSaleMethod().equals("1")) {
            if (Integer.parseInt(salesResult.getNoOfBids()) > 0) {
                total_amount = Integer.parseInt(salesResult.getCurrentBid());
            } else {
                total_amount = Integer.parseInt(salesResult.getOpeningBid());
            }
        } else {
            total_amount = Integer.parseInt(salesResult.getSalePrice());
        }



        float no_of_address = Float.parseFloat(salesResult.getNoOfAddress());
        double price_per_add = total_amount / no_of_address;

        holder.pricePerAddress.setText("$" + round(price_per_add,2));
        if (salesResult.getSaleOrRent().equals("1")) {
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
                holder.transferableTx.setText("");
                holder.transferableTx.setVisibility(View.GONE);
            }
        }
        else {
            holder.transferableTx.setText("");
            holder.transferableTx.setVisibility(View.GONE);
            holder.includedLay.setVisibility(View.VISIBLE);

        }

        if (salesResult.getSaleOrRent().equals("1")) {
            if (salesResult.getSaleMethod().equals("1")) {
                holder.status.setText("Auction");
                holder.minitermLay.setVisibility(View.GONE);
                holder.openingBidTitle.setText("OPENING BID:");
                holder.bidsTitle.setVisibility(View.VISIBLE);
                holder.bids.setVisibility(View.VISIBLE);
                if (Integer.parseInt(salesResult.getNoOfBids()) > 0) {
                    holder.openingBidTitle.setText("CURRENT BID:");
                    String bidS = "$" + salesResult.getCurrentBid();
                    holder.openingBid.setText(bidS);
                } else {
                    holder.openingBidTitle.setText("OPENING BID:");
                    String bidS = "$" + salesResult.getOpeningBid();
                    holder.openingBid.setText(bidS);
                }
            } else {
                holder.status.setText("Buy");
                holder.minitermLay.setVisibility(View.GONE);
                holder.openingBidTitle.setText("SALE PRICE:");
                holder.bidsTitle.setVisibility(View.GONE);
                holder.bids.setVisibility(View.GONE);
                String s= "$" + salesResult.getSalePrice();
                holder.openingBid.setText(s);
            }
        } else {
            if (salesResult.getSaleMethod().equals("1")) {
                holder.status.setText("Auction");
                holder.minitermLay.setVisibility(View.VISIBLE);
                holder.openingBidTitle.setText("OPENING BID:");
                holder.bidsTitle.setVisibility(View.VISIBLE);
                holder.bids.setVisibility(View.VISIBLE);
                if (Integer.parseInt(salesResult.getNoOfBids()) > 0) {
                    holder.openingBidTitle.setText("CURRENT BID:");
                    String bidS = "$" + salesResult.getCurrentBid();
                    holder.openingBid.setText(bidS);
                } else {
                    holder.openingBidTitle.setText("OPENING BID:");
                    String bidS = "$" + salesResult.getOpeningBid();
                    holder.openingBid.setText(bidS);
                }
            } else {
                holder.status.setText("Buy");
                holder.minitermLay.setVisibility(View.VISIBLE);
                holder.openingBidTitle.setText("SALE PRICE:");
                holder.bidsTitle.setVisibility(View.GONE);
                holder.bids.setVisibility(View.GONE);
                String s= "$" + salesResult.getSalePrice();
                holder.openingBid.setText(s);
            }
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!salesResult.getUserId().equals(MyPreferenceManager.getInstance().getPref(Constants.USER_ID))) {
                    if (salesResult.getSaleOrRent().equals("1")) {
                        if (salesResult.getSaleMethod().equals("1")) {
                            Intent intent = new Intent(context, BuyAuctionActivity.class);
                            intent.putExtra("SALE_TYPE", Constants.PURCHASE);
                            intent.putExtra("SALE_METHOD", Constants.AUCTION);
                            intent.putExtra("list_id", String.valueOf(salesResult.getListId()));
                            intent.putExtra("from_watchlist", "");

                            context.startActivity(intent);
                        } else {
                            Intent intent = new Intent(context, BuyActivity.class);
                            intent.putExtra("SALE_TYPE", Constants.PURCHASE);
                            intent.putExtra("SALE_METHOD", Constants.BUY);
                            intent.putExtra("list_id", String.valueOf(salesResult.getListId()));
                            intent.putExtra("from_watchlist", "");
                            context.startActivity(intent);
                        }
                    } else {
                        if (salesResult.getSaleMethod().equals("1")) {
                            Intent intent = new Intent(context, BuyAuctionActivity.class);
                            intent.putExtra("SALE_TYPE", Constants.RENT);
                            intent.putExtra("SALE_METHOD", Constants.AUCTION);
                            intent.putExtra("list_id", String.valueOf(salesResult.getListId()));
                            intent.putExtra("from_watchlist", "");

                            context.startActivity(intent);
                        } else {
                            Intent intent = new Intent(context, BuyActivity.class);
                            intent.putExtra("SALE_TYPE", Constants.RENT);
                            intent.putExtra("SALE_METHOD", Constants.BUY);
                            intent.putExtra("list_id", String.valueOf(salesResult.getListId()));
                            intent.putExtra("from_watchlist", "");
                            context.startActivity(intent);
                        }
                    }
                } else {
                    if (salesResult.getSaleOrRent().equals("1")) {
                        if (salesResult.getSaleMethod().equals("1")) {
                            Intent intent = new Intent(context, SellAuctionActivity.class);
                            intent.putExtra("SALE_TYPE", Constants.SALE);
                            intent.putExtra("SALE_METHOD", Constants.AUCTION);
                            intent.putExtra("list_id", String.valueOf(salesResult.getListId()));
                            context.startActivity(intent);
                        } else {
                            Intent intent = new Intent(context, SellActivity.class);
                            intent.putExtra("SALE_TYPE", Constants.SALE);
                            intent.putExtra("SALE_METHOD", Constants.SELL);
                            intent.putExtra("list_id", String.valueOf(salesResult.getListId()));
                            context.startActivity(intent);
                        }
                    } else {
                        if (salesResult.getSaleMethod().equals("1")) {
                            Intent intent = new Intent(context, SellAuctionActivity.class);
                            intent.putExtra("SALE_TYPE", Constants.LEASE);
                            intent.putExtra("SALE_METHOD", Constants.AUCTION);
                            intent.putExtra("list_id", String.valueOf(salesResult.getListId()));
                            context.startActivity(intent);
                        } else {
                            Intent intent = new Intent(context, SellActivity.class);
                            intent.putExtra("SALE_TYPE", Constants.LEASE);
                            intent.putExtra("SALE_METHOD", Constants.LEASE);
                            intent.putExtra("list_id", String.valueOf(salesResult.getListId()));
                            context.startActivity(intent);
                        }
                    }
                }
            }
        });
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @Override
    public int getItemCount() {
        return watchList.size();
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
        TextView minTerm;
        TextView status;
        RelativeLayout minitermLay;
        AppCompatImageView gotoDetail;
        RelativeLayout includedLay;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            openingBid = itemView.findViewById(R.id.opening_bid);
            endsIn = itemView.findViewById(R.id.ends_in);
            minTerm = itemView.findViewById(R.id.mini_term);
            status = itemView.findViewById(R.id.status);
            transferableTx = itemView.findViewById(R.id.transferable_tx);
            minitermLay = itemView.findViewById(R.id.mini_term_lay);
            openingBidTitle = itemView.findViewById(R.id.opening_bid_title);
            bidsTitle = itemView.findViewById(R.id.bids_title);
            bids = itemView.findViewById(R.id.bids);
            title = itemView.findViewById(R.id.title);
            gotoDetail = itemView.findViewById(R.id.go_to_detail);
            pricePerAddress = itemView.findViewById(R.id.price_per_address);
            includedLay = itemView.findViewById(R.id.included_lay);

            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.title));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.opening_bid));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.price_per_address));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.ends_in));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.bids));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.status));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.mini_term));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.status));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.transferable_tx));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.opening_bid_title));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.price_per_address_tx));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.ends_in_title));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.bids_title));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.min_term_title));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.included_tx));

        }
    }
}
