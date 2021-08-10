package com.xsusenet.ip4.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.xsusenet.ip4.Models.Notifications.Notification;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.UI.Sell.SellActivity;
import com.xsusenet.ip4.UI.SellAuction.SellAuctionActivity;
import com.xsusenet.ip4.Util.Constants;
import com.xsusenet.ip4.Util.Util;

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.MyViewHolder> {

    List<Notification> notifications;
    Context context;

    public NotificationsAdapter(Context context, List<Notification> notificationList) {
        this.context = context;
        this.notifications = notificationList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        return new NotificationsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Notification notification = notifications.get(position);

        holder.notificationText.setText(notification.getNotificationText());
        String[] dates = notification.getCreatedAt().split("T");
        String atS =  dates[1].split("\\.")[0];
        String dateS = dates[0] + " at " +atS;
        holder.dateText.setText(dateS);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SellAuctionActivity.class);
                intent.putExtra("SALE_METHOD", Constants.AUCTION);
                intent.putExtra("list_id", notification.getListId());
                context.startActivity(intent);
            }

        });

    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView notificationText;
        TextView dateText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            notificationText = itemView.findViewById(R.id.notification_text);
            dateText = itemView.findViewById(R.id.dateText);

            Util.getUtils().overrideFontsSemiBold(context, notificationText);
            Util.getUtils().overrideFontsRegular(context, dateText);
        }
    }
}