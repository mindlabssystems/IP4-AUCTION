package com.xsusenet.ip4.UI.Buy;

import com.xsusenet.ip4.Models.Country.Country;
import com.xsusenet.ip4.Models.Detail.DetailList;
import com.xsusenet.ip4.Models.User.User;

import java.util.List;

public interface BuyActivityContract {

    interface presenter {
        void getDetail(String listId);

        void addToWatchList(String listId);
    }

    interface view {
        void ShowProgress();

        void StopProgress();


        void setList(DetailList lists);

        void WatchResult(boolean b, String message);

        void WatchRemoveResult(boolean b, String message);
    }
}
