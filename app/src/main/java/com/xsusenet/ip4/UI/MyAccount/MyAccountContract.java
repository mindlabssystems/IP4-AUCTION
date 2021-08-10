package com.xsusenet.ip4.UI.MyAccount;


import com.xsusenet.ip4.Models.Country.Country;
import com.xsusenet.ip4.Models.User.User;

import java.util.List;

public interface MyAccountContract {
    interface presenter {

        void getProfile();
    }

    interface view {
        void ShowProgress();

        void StopProgress();
        void  PushNotificationsResult(boolean status, String s);

        void setValues(User user);

        void EmailNotificationsResult(boolean b, String message);
    }
}
