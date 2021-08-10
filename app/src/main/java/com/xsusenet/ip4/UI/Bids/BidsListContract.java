package com.xsusenet.ip4.UI.Bids;

import com.xsusenet.ip4.Models.Bids.Bid;
import com.xsusenet.ip4.Models.Detail.DetailList;

import java.util.List;

public interface BidsListContract {
    interface presenter {

        void getBidList(String listId,int page_no);
    }

    interface view {
        void ShowProgress();

        void StopProgress();


        void setList(List<Bid> result);
    }
}
