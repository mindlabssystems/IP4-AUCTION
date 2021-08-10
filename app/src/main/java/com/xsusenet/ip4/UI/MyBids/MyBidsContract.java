package com.xsusenet.ip4.UI.MyBids;

import com.xsusenet.ip4.Models.Country.Country;
import com.xsusenet.ip4.Models.MyBids.Bid;
import com.xsusenet.ip4.Models.User.User;

import java.util.List;

public interface MyBidsContract {
    interface presenter {
        void getMyBids();
    }

    interface view {
        void ShowProgress();

        void StopProgress();


        void showResult(boolean b, List<Bid> all);
        void SetTime(String time);
    }
}
