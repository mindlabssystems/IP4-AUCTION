package com.xsusenet.ip4.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xsusenet.ip4.Models.Purchases.All;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.Util.Util;

import java.util.List;

public class PurchasesAdapter extends RecyclerView.Adapter<PurchasesAdapter.MyViewHolder> {
    Context context;
    List<All> purchasesList;
    PaymentsAdapter paymentsAdapter;
    boolean PAYMENTS_HISTORY;
    OnCancelItemClickListener itemClickListener;

    public PurchasesAdapter(Context context, List<All> purchasesList, OnCancelItemClickListener itemClickListener) {
        this.context = context;
        this.purchasesList = purchasesList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public PurchasesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchases_item, parent, false);
        return new PurchasesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchasesAdapter.MyViewHolder holder, int position) {
        All all = purchasesList.get(position);

        if (!all.getStripeStatus().isEmpty()) {
            holder.subLay.setVisibility(View.VISIBLE);
            if (all.getStripeStatus().toLowerCase().equals("active")) {
                holder.subStatus.setText("Active");
                holder.cancelSub.setVisibility(View.VISIBLE);
            } else {
                holder.subStatus.setText("Cancelled");
                holder.cancelSub.setVisibility(View.GONE);
            }
        } else {
            holder.subLay.setVisibility(View.GONE);
            holder.cancelSub.setVisibility(View.GONE);
        }

        if(all.getPaymentStatus().equals("2")){
            holder.orderStatus.setText("Pending Payment");
        }else if(all.getPaymentStatus().equals("1")){
            holder.orderStatus.setText("Paid");
        }else{
            holder.orderStatus.setText("Failed");

        }
        holder.transactionIdTx.setText("Transaction ID\n" + all.getTransactionId());
        holder.dateTx.setText("Date\n" + all.getTransactionDate());
        holder.subtotalTx.setText("$"+all.getListAmount());
        holder.transactionFeeTx.setText("$"+all.getTransferFee());
        holder.totalTx.setText("$"+all.getTotalAmount());
        if (all.getPayments() != null) {
            if (all.getPayments().size() > 0) {
                holder.payment_history_lay.setVisibility(View.VISIBLE);
                paymentsAdapter = new PaymentsAdapter(context, all.getPayments());
                holder.payments_rec.setAdapter(paymentsAdapter);
            } else {
                holder.payment_history_lay.setVisibility(View.GONE);
            }
        }else{
            holder.payment_history_lay.setVisibility(View.GONE);

        }

        holder.cancelSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClicked(position);
            }
        });
        holder.payment_history_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!PAYMENTS_HISTORY) {
                    PAYMENTS_HISTORY = true;
                    holder.view_payments_but.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_up_arrow));
                    holder.payments_rec.setVisibility(View.VISIBLE);
                } else {
                    PAYMENTS_HISTORY = false;
                    holder.view_payments_but.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_down_arrow_));
                    holder.payments_rec.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return purchasesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView subtotalTx, transactionFeeTx, totalTx, dateTx, transactionIdTx, payment_history, subStatus, subStatusTitle,orderStatus;
        AppCompatImageView view_payments_but;
        RelativeLayout payment_history_lay;
        RecyclerView payments_rec;
        AppCompatButton cancelSub;
        ConstraintLayout subLay;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            subtotalTx = itemView.findViewById(R.id.subtotal_tx);
            transactionFeeTx = itemView.findViewById(R.id.transaction_fee);
            totalTx = itemView.findViewById(R.id.total_amount);
            dateTx = itemView.findViewById(R.id.date_of_purchase);
            transactionIdTx = itemView.findViewById(R.id.transaction_id);
            payment_history = itemView.findViewById(R.id.payment_history);
            view_payments_but = itemView.findViewById(R.id.view_payments);
            payment_history_lay = itemView.findViewById(R.id.payment_history_lay);
            payments_rec = itemView.findViewById(R.id.payments_rec);
            subStatus = itemView.findViewById(R.id.sub_status);
            orderStatus = itemView.findViewById(R.id.ord_status);
            subStatusTitle = itemView.findViewById(R.id.subscription_status);
            cancelSub = itemView.findViewById(R.id.cancel_subscription);
            subLay = itemView.findViewById(R.id.constraint_);


            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.subtotal_tx));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.transaction_fee));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.total_amount));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.date_of_purchase));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.transaction_id));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.payment_history));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.sub_status));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.ord_status));

            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.subtotal_title));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.transaction_fee_title));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.total_title));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.subscription_status));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.order_status));
        }
    }
    public interface OnCancelItemClickListener {
        void onItemClicked(int position);
    }

}
