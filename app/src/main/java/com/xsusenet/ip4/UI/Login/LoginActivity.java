package com.xsusenet.ip4.UI.Login;

import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.xsusenet.ip4.MainActivity;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.UI.ForgotPass.ForgotPassInterface;
import com.xsusenet.ip4.UI.ForgotPass.Forgotpass;
import com.xsusenet.ip4.UI.ResendMail.ResendMail;
import com.xsusenet.ip4.UI.ResendMail.ResendMailInterface;
import com.xsusenet.ip4.Util.Util;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;


public class LoginActivity extends DaggerAppCompatActivity implements LoginContract.view, ForgotPassInterface, ResendMailInterface {

    @BindView(R.id.LoginBut)
    RelativeLayout LoginBut;
    @BindView(R.id.email_tx)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.forgot_pwd)
    TextView forgotPwd;
    @BindView(R.id.resend_email)
    TextView resendMail;
    @BindView(R.id.progress)
    LinearLayout progress;

    @Inject
    LoginPresenterImpl presenter;

    @Inject
    Util util;

    String Semail, Spassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initUi();
    }

    private void initUi() {
        Util.getUtils().overrideFontsBold(LoginActivity.this, findViewById(R.id.text1));
        Util.getUtils().overrideFontsRegular(LoginActivity.this, findViewById(R.id.text2));
        Util.getUtils().overrideFontsRegular(LoginActivity.this, findViewById(R.id.email_tx));
        Util.getUtils().overrideFontsRegular(LoginActivity.this, findViewById(R.id.password));
        Util.getUtils().overrideFontsSemiBold(LoginActivity.this, findViewById(R.id.forgot_pwd));
        Util.getUtils().overrideFontsSemiBold(LoginActivity.this, findViewById(R.id.resend_email));
        Util.getUtils().overrideFontsBold(LoginActivity.this, findViewById(R.id.login_text));
    }


    @OnClick(R.id.LoginBut)
    void loginbut() {
        if (Util.getUtils().isNetworkAvailable()) {
            if (!email.getText().toString().equals("") && !password.getText().toString().equals("")) {
                if (checkEmail() && checkPassword()) {
                    presenter.Login(Semail, Spassword);
                }
            } else {
                util.displayToast(this, getResources().getString(R.string.login_id_and_pwd), 2);
            }
        } else {
            util.displayToast(this, getString(R.string.no_internet), 2);
        }
    }

    private boolean checkPassword() {
        Spassword = password.getText().toString().trim();
        if (!Spassword.isEmpty()) {
            return true;
        } else {
            util.displayToast(this, getResources().getString(R.string.invalid_pwd), 2);
            return false;
        }
    }

    private boolean checkEmail() {
        Semail = email.getText().toString().trim();
        if (!Semail.isEmpty() && util.isValidEmail(Semail)) {
            return true;
        } else {
            util.displayToast(this, getResources().getString(R.string.invalid_email), 2);
            return false;
        }
    }

    @OnClick(R.id.forgot_pwd)
    void setForgotPwd() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        DialogFragment newFragment = Forgotpass.newInstance();
        newFragment.show(ft,"dialog");
    }
    @OnClick(R.id.resend_email)
    void setResendMail() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        DialogFragment newFragment = ResendMail.newInstance();
        newFragment.show(ft, "dialog1");
    }

    @Override
    public void ShowProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void StopProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showResult(boolean b, String message) {
        if (b) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            util.displayToast(this, message, 2);
        }
    }

    @Override
    public void showResult(boolean b, String message, String resend) {
        if (!b) {
            util.displayToast(this, message, 2);
            resendMail.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void ForgotPwdResult(boolean b, String message) {
        if (b) {
            util.displayToast(this, message, 1);
        } else {
            util.displayToast(this, getResources().getString(R.string.invalid_email), 2);
        }
    }
    @Override
    public void ResendMailResult(boolean b, String message) {
        if (b) {
            util.displayToast(this, message, 1);
        } else {
            util.displayToast(this, getResources().getString(R.string.invalid_email), 2);
        }
    }

    @Override
    public void onPasswordChange(String email) {
        if (util.isNetworkAvailable())
            presenter.callforgotPwd(email);
    }
    @Override
    public void onResend(String email) {
        if (util.isNetworkAvailable())
            presenter.callResendMail(email);
    }


}