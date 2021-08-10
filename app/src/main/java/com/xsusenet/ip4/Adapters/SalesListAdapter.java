package com.xsusenet.ip4.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.Models.Sales.SalesResult;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.UI.AllSales.SalesListPresenterImpl;
import com.xsusenet.ip4.UI.Buy.BuyActivity;
import com.xsusenet.ip4.UI.BuyAuction.BuyAuctionActivity;
import com.xsusenet.ip4.UI.Sell.SellActivity;
import com.xsusenet.ip4.UI.SellAuction.SellAuctionActivity;
import com.xsusenet.ip4.Util.Constants;
import com.xsusenet.ip4.Util.Util;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import cn.iwgang.countdownview.CountdownView;


public class SalesListAdapter extends RecyclerView.Adapter<SalesListAdapter.MyViewHolder> {
    Context context;
    List<SalesResult> salesResultList;

    CountDownTimer countDownTimer;
    int total_amount;
    String sCDT;

    SalesListPresenterImpl presenter;

    public SalesListAdapter(Context jobsFragment, List<SalesResult> salesResults) {
        this.context = jobsFragment;
        this.salesResultList = salesResults;

        this.sCDT = MyPreferenceManager.getInstance().getPref("STIME", "");
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_item, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_item_test, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onViewAttachedToWindow(MyViewHolder holder) {

        int pos = holder.getAdapterPosition();
//            Log.d("MyViewHolder", String.format("mCvCountdownView %s is attachedToWindow", pos));

        SalesResult salesResult = salesResultList.get(pos);

        if (salesResult.getSaleMethod().equals("1")) {

            holder.end_date.setVisibility(View.GONE);
            holder.counterLay.setVisibility(View.VISIBLE);

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
        } else {
            holder.end_date.setVisibility(View.VISIBLE);
            holder.counterLay.setVisibility(View.GONE);
            holder.end_date.setText("No Expiry");
        }

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SalesResult salesResult = salesResultList.get(position);
//        holder.endsIn.setText(salesResult.getEndDate());
        holder.bids.setText(salesResult.getNoOfBids());
        if (!salesResult.getRentMinTerm().isEmpty()) {
            if (Integer.parseInt(salesResult.getRentMinTerm()) > 1) {
                holder.minTerm.setText(salesResult.getRentMinTerm() + " Months");
            } else if (Integer.parseInt(salesResult.getRentMinTerm()) == 1) {
                holder.minTerm.setText(salesResult.getRentMinTerm() + " Month");
            }
        }
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

        String blockS = "/" + salesResult.getBlockSize();
        title.append(blockS);
        title.append(" - ");
        title.append(salesResult.getRegionName());
//        StringBuilder title = new StringBuilder();
//        title.append("/");
//        title.append(salesResult.getBlockSize());
//        title.append(" Block registered in ");
//        title.append(salesResult.getRegionName());
        holder.title.setText(title);

        String cdt = "";
//        cdt = Util.getUtils().getCDT();
        if (sCDT != null && !sCDT.isEmpty()) {
            cdt = sCDT.split(" ")[0].split("-")[1] + "/" + sCDT.split(" ")[0].split("-")[2] + "/" + sCDT.split(" ")[0].split("-")[0] + "  " + sCDT.split(" ")[1];
        } else {
            cdt = Util.getUtils().getCDT();
        }
        String[] ends_d_t = salesResult.getEndDate().split(" ");
        String[] ends = ends_d_t[0].split("-");
        if (salesResult.getSaleMethod().equals("1")) {
            holder.end_date.setVisibility(View.GONE);
            holder.counterLay.setVisibility(View.VISIBLE);
            String endsdt = ends[1] + "/" + ends[2] + "/" + ends[0] + " 23:59:59";
            long minites = Util.getUtils().differenceInMinit(cdt, endsdt);

            if (minites > 0) {
                holder.countdownView.start(minites);
            } else {
                holder.countdownView.stop();
                holder.countdownView.allShowZero();
            }
        } else {
            holder.end_date.setVisibility(View.VISIBLE);
            holder.counterLay.setVisibility(View.GONE);
            holder.end_date.setText("No Expiry");
        }

       /* holder.countdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                holder.countdownView.setVisibility(View.GONE);
                holder.times_up.setVisibility(View.VISIBLE);
            }
        });
*/


//        holder.bindTimer(holder, minit);
//        holder.easyCountDownTextview.setTime((int) minit[0],(int) minit[1],(int)minit[2],(int) minit[3]);
//        holder.easyCountDownTextview.startTimer();

        if (salesResult.getUserId().equals(MyPreferenceManager.getInstance().getPref(Constants.USER_ID))) {
//            holder.my_product.setVisibility(View.VISIBLE);
            holder.rootLay.setBackgroundColor(context.getResources().getColor(R.color.yellow_light));
        } else {
//            holder.my_product.setVisibility(View.GONE);
            holder.rootLay.setBackgroundColor(Color.WHITE);
        }

        if (salesResult.getSaleMethod().equals("1")) {
            if (Integer.parseInt(salesResult.getNoOfBids()) > 0) {
                total_amount = Integer.parseInt(salesResult.getHighestbidvalue());
            } else {
                total_amount = Integer.parseInt(salesResult.getOpeningBid());
            }
        } else {
            total_amount = Integer.parseInt(salesResult.getSalePrice());
        }


        float no_of_address = Float.parseFloat(salesResult.getNoOfAddress());
        double price_per_add = total_amount / no_of_address;
        String s = "$" + round(price_per_add, 2);
        holder.pricePerAddress.setText(s);

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
                holder.transferableTx.setVisibility(View.INVISIBLE);

            }
        } else {
            holder.transferableTx.setText("");
            holder.transferableTx.setVisibility(View.GONE);
            holder.includedLay.setVisibility(View.VISIBLE);

        }

        if (salesResult.getSaleOrRent().equals("1")) {
            if (salesResult.getSaleMethod().equals("1")) {
                holder.status.setText(R.string.auction_u);
                holder.minitermLay.setVisibility(View.GONE);
                holder.bidsTitle.setVisibility(View.VISIBLE);
                holder.bids.setVisibility(View.VISIBLE);
                if (Integer.parseInt(salesResult.getNoOfBids()) > 0) {
                    holder.openingBidTitle.setText("CURRENT BID:");
                    String bid = "$" + salesResult.getHighestbidvalue();
                    holder.openingBid.setText(bid);
                } else {
                    holder.openingBidTitle.setText("OPENING BID:");
                    String bid = "$" + salesResult.getOpeningBid();
                    holder.openingBid.setText(bid);
                }
            } else {
                holder.status.setText(R.string.buy_u);
                holder.minitermLay.setVisibility(View.GONE);
                holder.openingBidTitle.setText("SALE PRICE:");
                holder.bidsTitle.setVisibility(View.GONE);
                holder.bids.setVisibility(View.GONE);
                String bid = "$" + salesResult.getSalePrice();
                holder.openingBid.setText(bid);
            }
        } else {
            if (salesResult.getSaleMethod().equals("1")) {
                holder.status.setText(R.string.auction_u);
                holder.minitermLay.setVisibility(View.VISIBLE);
                holder.bidsTitle.setVisibility(View.VISIBLE);
                holder.bids.setVisibility(View.VISIBLE);
                if (Integer.parseInt(salesResult.getNoOfBids()) > 0) {
                    holder.openingBidTitle.setText("CURRENT BID:");
                    String bid = "$" + salesResult.getHighestbidvalue();
                    holder.openingBid.setText(bid);
                } else {
                    holder.openingBidTitle.setText("OPENING BID:");
                    String bid = "$" + salesResult.getOpeningBid();
                    holder.openingBid.setText(bid);
                }

            } else {
                holder.status.setText(R.string.buy_u);
                holder.minitermLay.setVisibility(View.VISIBLE);
                holder.openingBidTitle.setText("SALE PRICE:");
                holder.bidsTitle.setVisibility(View.GONE);
                holder.bids.setVisibility(View.GONE);
                String bid = "$" + salesResult.getSalePrice();
                holder.openingBid.setText(bid);
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
                            intent.putExtra("list_id", salesResult.getId());
                            context.startActivity(intent);
                        } else {
                            Intent intent = new Intent(context, BuyActivity.class);
                            intent.putExtra("SALE_TYPE", Constants.PURCHASE);
                            intent.putExtra("SALE_METHOD", Constants.BUY);
                            intent.putExtra("list_id", salesResult.getId());
                            context.startActivity(intent);
                        }
                    } else {
                        if (salesResult.getSaleMethod().equals("1")) {
                            Intent intent = new Intent(context, BuyAuctionActivity.class);
                            intent.putExtra("SALE_TYPE", Constants.RENT);
                            intent.putExtra("SALE_METHOD", Constants.AUCTION);
                            intent.putExtra("list_id", salesResult.getId());
                            context.startActivity(intent);
                        } else {
                            Intent intent = new Intent(context, BuyActivity.class);
                            intent.putExtra("SALE_TYPE", Constants.RENT);
                            intent.putExtra("SALE_METHOD", Constants.BUY);
                            intent.putExtra("list_id", salesResult.getId());
                            context.startActivity(intent);
                        }
                    }
                } else {
                    if (salesResult.getSaleOrRent().equals("1")) {
                        if (salesResult.getSaleMethod().equals("1")) {
                            Intent intent = new Intent(context, SellAuctionActivity.class);
                            intent.putExtra("SALE_TYPE", Constants.SALE);
                            intent.putExtra("SALE_METHOD", Constants.AUCTION);
                            intent.putExtra("list_id", salesResult.getId());
                            context.startActivity(intent);
                        } else {
                            Intent intent = new Intent(context, SellActivity.class);
                            intent.putExtra("SALE_TYPE", Constants.SALE);
                            intent.putExtra("SALE_METHOD", Constants.SELL);
                            intent.putExtra("list_id", salesResult.getId());
                            context.startActivity(intent);
                        }
                    } else {
                        if (salesResult.getSaleMethod().equals("1")) {
                            Intent intent = new Intent(context, SellAuctionActivity.class);
                            intent.putExtra("SALE_TYPE", Constants.LEASE);
                            intent.putExtra("SALE_METHOD", Constants.AUCTION);
                            intent.putExtra("list_id", salesResult.getId());
                            context.startActivity(intent);
                        } else {
                            Intent intent = new Intent(context, SellActivity.class);
                            intent.putExtra("SALE_TYPE", Constants.LEASE);
                            intent.putExtra("SALE_METHOD", Constants.LEASE);
                            intent.putExtra("list_id", salesResult.getId());
                            context.startActivity(intent);
                        }
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return salesResultList.size();
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public void notifyTimeChange(String time) {
        this.sCDT = time;
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
        RelativeLayout minitermLay, counterLay;
        AppCompatImageView gotoDetail;
        //        AppCompatImageView my_product;
        ConstraintLayout rootLay;

        CountdownView countdownView;


        TextView times_up, end_date;
        RelativeLayout includedLay;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            openingBid = itemView.findViewById(R.id.opening_bid);
//            endsIn = itemView.findViewById(R.id.ends_in);
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
//            easyCountDownTextview = itemView.findViewById(R.id.ends_in);
            countdownView = itemView.findViewById(R.id.ends_in);
            end_date = itemView.findViewById(R.id.ends_in_date);
            counterLay = itemView.findViewById(R.id.counterLay);
            times_up = itemView.findViewById(R.id.times_up);

//            my_product = itemView.findViewById(R.id.my_product);
            rootLay = itemView.findViewById(R.id.root);
            includedLay = itemView.findViewById(R.id.included_lay);

            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.title));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.opening_bid));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.price_per_address));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.ends_in));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.bids));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.status));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.mini_term));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.status));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.times_up));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.ends_in_date));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.transferable_tx));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.opening_bid_title));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.price_per_address_tx));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.ends_in_title));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.bids_title));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.min_term_title));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.included_tx));
        }

        public void bindTimer(MyViewHolder holder, long minit) {
            if (countDownTimer != null)
                countDownTimer.cancel();
            countDownTimer = new CountDownTimer(minit, 1000) {
                public void onTick(long millisUntilFinished) {
                    long millis = millisUntilFinished;
                    //Convert milliseconds into hour,minute and seconds
                    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                    holder.endsIn.setText(hms);//set text
                }

                public void onFinish() {

                    holder.endsIn.setText("TIME'S UP!!"); //On finish change timer text
                    countDownTimer = null;//set CountDownTimer to null

                }
            }.start();


        }
    }
}
