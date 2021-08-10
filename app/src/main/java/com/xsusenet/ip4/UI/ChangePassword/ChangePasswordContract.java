package com.xsusenet.ip4.UI.ChangePassword;

import com.xsusenet.ip4.Models.Country.Country;

import java.util.List;

public interface ChangePasswordContract {
    interface presenter {
    }

    interface view {
        void ShowProgress();

        void StopProgress();

        void showResult(boolean b, String message);



    }
}
