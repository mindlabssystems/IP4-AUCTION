package com.xsusenet.ip4.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.xsusenet.ip4.Models.Purchases.All;
import com.xsusenet.ip4.Models.Purchases.Payment;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.Util.Util;

import java.util.List;

public class PaymentsAdapter extends RecyclerView.Adapter<PaymentsAdapter.MyViewHolder> {
    Context context;
    List<Payment> paymentsList;


    public PaymentsAdapter(Context context, List<Payment> paymentsList) {
        this.context = context;
        this.paymentsList = paymentsList;
    }

    @NonNull
    @Override
    public PaymentsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_item, parent, false);
        return new PaymentsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentsAdapter.MyViewHolder holder, int position) {
        Payment all = paymentsList.get(position);
        holder.transactionIdTx.setText("Subscription ID\n" + all.getSubscriptionId());
        holder.dateTx.setText("Paid Date\n" + all.getPaidDate());
        holder.totalTx.setText(all.getAmount().toString());
        if (position >= paymentsList.size() - 1) {
            holder.view.setVisibility(View.GONE);
        } else {
            holder.view.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public int getItemCount() {
        return paymentsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView subtotalTx, transactionFeeTx, totalTx, dateTx, transactionIdTx;
        View view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

//            subtotalTx = itemView.findViewById(R.id.subtotal_tx);
//            transactionFeeTx = itemView.findViewById(R.id.transaction_fee);
            totalTx = itemView.findViewById(R.id.total_amount);
            dateTx = itemView.findViewById(R.id.date_of_purchase);
            transactionIdTx = itemView.findViewById(R.id.transaction_id);
            view = itemView.findViewById(R.id.view_2);

            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.subtotal_tx));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.transaction_fee));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.total_amount));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.date_of_purchase));
            Util.getUtils().overrideFontsBold(context, itemView.findViewById(R.id.transaction_id));


            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.subtotal_title));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.transaction_fee_title));
            Util.getUtils().overrideFontsRegular(context, itemView.findViewById(R.id.total_title));
        }

    }
}