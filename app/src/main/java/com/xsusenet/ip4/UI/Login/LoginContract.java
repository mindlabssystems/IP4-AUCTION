package com.xsusenet.ip4.UI.Login;


public interface LoginContract {
    interface presenter {
    }

    interface view {

        void ShowProgress();

        void StopProgress();

        void showResult(boolean b, String message);
        void showResult(boolean b, String message,String resend);


        void ForgotPwdResult(boolean b, String message);
        void ResendMailResult(boolean b, String message);
    }
}