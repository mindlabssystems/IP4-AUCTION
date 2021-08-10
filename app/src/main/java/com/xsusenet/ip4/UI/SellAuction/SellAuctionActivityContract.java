package com.xsusenet.ip4.UI.SellAuction;

import com.xsusenet.ip4.Models.Detail.DetailList;

import java.util.List;

public interface SellAuctionActivityContract {
    interface presenter {
        void getDetail(String listId);
    }

    interface view {
        void ShowProgress();

        void StopProgress();

        void setList(DetailList lists);
    }
}
