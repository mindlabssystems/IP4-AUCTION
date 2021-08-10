package com.xsusenet.ip4.UI.ChangePassword;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;

import com.xsusenet.ip4.R;
import com.xsusenet.ip4.Util.Util;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

public class ChangePasswordActivity extends DaggerAppCompatActivity implements ChangePasswordContract.view {

    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.new_password)
    EditText newPassword;
    @BindView(R.id.confirm_new_password)
    EditText confirmNewPassword;

    @BindView(R.id.save_text)
    TextView saveText;
    @BindView(R.id.submit_but)
    RelativeLayout submitBut;

    @Inject
    Util util;

    @Inject
    ChangePasswordPresenterImpl presenter;

    String Spassword, SconfirmPassword;
    @BindView(R.id.progress)
    LinearLayout progress;
    @BindView(R.id.back_img)
    AppCompatImageView backImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ButterKnife.bind(this);
        initUi();
    }

    private void initUi() {
        Util.getUtils().overrideFontsBold(ChangePasswordActivity.this, text1);
        Util.getUtils().overrideFontsRegular(ChangePasswordActivity.this, text2);
        Util.getUtils().overrideFontsRegular(ChangePasswordActivity.this, newPassword);
        Util.getUtils().overrideFontsRegular(ChangePasswordActivity.this, confirmNewPassword);
        Util.getUtils().overrideFontsBold(ChangePasswordActivity.this, saveText);
    }
    @OnClick(R.id.back_img)
    void onBack(){
        onBackPressed();
    }


    @OnClick(R.id.submit_but)
    void setSubmitBut() {
        if (util.isNetworkAvailable()) {
            if (checkPassword() && checkConfirmPassword())
                presenter.changePassword(Spassword);
        } else {
            util.displayToast(this, getResources().getString(R.string.network_unavailable), 2);
        }
    }

    private boolean checkPassword() {
        Spassword = newPassword.getText().toString().trim();
        if (!Spassword.isEmpty()) {
            return true;
        } else {
            util.displayToast(this, getResources().getString(R.string.enter_pwd), 2);
            return false;
        }
    }

    private boolean checkConfirmPassword() {
        SconfirmPassword = confirmNewPassword.getText().toString().trim();
        if (!SconfirmPassword.isEmpty()) {
            if (Spassword.equals(SconfirmPassword)) {
                return true;
            } else {
                util.displayToast(this, getResources().getString(R.string.pwd_not_match), 2);
                return false;
            }
        } else {
            util.displayToast(this, getResources().getString(R.string.confirm_pwd), 2);
            return false;
        }
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
            util.displayToast(this, "Your Password Updated", 1);
            onBackPressed();
        } else
            util.displayToast(this, "Failed", 2);
    }
}
