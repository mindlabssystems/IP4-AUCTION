package com.xsusenet.ip4.UI.Register;

public interface RegisterContract {
    interface presenter{
    }
    interface view
    {

        void ShowProgress();

        void StopProgress();

        void showResult(boolean b, String message);
    }
}
