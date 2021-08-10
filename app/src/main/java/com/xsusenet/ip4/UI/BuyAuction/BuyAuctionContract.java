package com.xsusenet.ip4.UI.BuyAuction;

import com.xsusenet.ip4.Models.Detail.DetailList;

import java.util.List;

public interface BuyAuctionContract {
    interface presenter {
        void getDetail(String listId);


        void makeAnOffer(String bidAmount, String listId);

        void addToWatchList(String listId);
    }

    interface view {
        void ShowProgress();

        void StopProgress();

        void setList(DetailList lists);

        void showResult(boolean b, String message);

        void WatchResult(boolean b, String message);

        void WatchRemoveResult(boolean b, String message);
        void SetTime(String time);
    }
}
