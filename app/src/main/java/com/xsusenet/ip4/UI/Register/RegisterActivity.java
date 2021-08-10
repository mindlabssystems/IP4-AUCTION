package com.xsusenet.ip4.UI.Register;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;

import com.xsusenet.ip4.R;
import com.xsusenet.ip4.TermsActivity;
import com.xsusenet.ip4.UI.Login.LoginActivity;
import com.xsusenet.ip4.Util.Util;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

public class RegisterActivity extends DaggerAppCompatActivity implements RegisterContract.view {

    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.confirm_password)
    EditText confirmPassword;
    @BindView(R.id.checkbox)
    AppCompatImageView checkbox;
    @BindView(R.id.RegisterBut)
    RelativeLayout RegisterBut;

    @Inject
    Util util;

    @Inject
    RegisterPresenterImpl presenter;


    String Semail, Spassword, Sfirstname, Slastname, SconfirmPassword;
    @BindView(R.id.first_name)
    EditText firstName;
    @BindView(R.id.last_name)
    EditText lastName;
    @BindView(R.id.accept_terms)
    TextView acceptTerms;
    boolean terms = false;
    @BindView(R.id.progress)
    LinearLayout progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initUi();
    }

    private void initUi() {
        Util.getUtils().overrideFontsBold(RegisterActivity.this, findViewById(R.id.text1));
        Util.getUtils().overrideFontsRegular(RegisterActivity.this, findViewById(R.id.text2));
        Util.getUtils().overrideFontsRegular(RegisterActivity.this, password);
        Util.getUtils().overrideFontsRegular(RegisterActivity.this, email);
        Util.getUtils().overrideFontsRegular(RegisterActivity.this, confirmPassword);
        Util.getUtils().overrideFontsSemiBold(RegisterActivity.this, findViewById(R.id.accept_terms));
        Util.getUtils().overrideFontsBold(RegisterActivity.this, findViewById(R.id.register_text));
        acceptTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, TermsActivity.class);
                intent.putExtra("from","register");
                intent.putExtra("url","http://ip4auction.mindmockups.com/ip4sales/api/terms");
                startActivity(intent);

            }
        });
    }

    @OnClick(R.id.RegisterBut)
    void setRegisterBut() {
        if (util.isNetworkAvailable()) {
            if (checkEmail() && checkFirstname()   && checkTermsStatus())
                presenter.callRegister(Semail, Spassword, Sfirstname, Slastname);
        } else {
            util.displayToast(this, getResources().getString(R.string.network_unavailable), 2);
        }
    }

    private boolean checkTermsStatus() {
        if (terms) {
            return true;
        } else {
            util.displayToast(this, "Accept terms and conditions", 2);
            return false;
        }
    }


    @OnClick(R.id.checkbox)
    void checkBox() {
        if (!terms) {
            String uri = "@drawable/ic_tick";  // where myresource (without the extension) is the file

            int imageResource = getResources().getIdentifier(uri, null, getPackageName());

            Drawable res = getResources().getDrawable(imageResource);
            checkbox.setImageDrawable(res);
            terms = true;
        } else {
            terms = false;
            String uri = "@drawable/ic_check_box";  // where myresource (without the extension) is the file

            int imageResource = getResources().getIdentifier(uri, null, getPackageName());

            Drawable res = getResources().getDrawable(imageResource);
            checkbox.setImageDrawable(res);
        }
    }

    private boolean checkConfirmPassword() {
        SconfirmPassword = confirmPassword.getText().toString().trim();
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

    private boolean checkLastname() {
        Slastname = lastName.getText().toString().trim();
        if (!Slastname.isEmpty()) {
            return true;
        } else {
            util.displayToast(this, getResources().getString(R.string.last_name_must_not_empty), 2);
            return false;
        }
    }

    private boolean checkFirstname() {
        Sfirstname = firstName.getText().toString().trim();
        if (!Sfirstname.isEmpty()) {
            return true;
        } else {
            util.displayToast(this, getResources().getString(R.string.first_name_must_not_empty), 2);
            return false;
        }
    }

    private boolean checkPassword() {
        Spassword = password.getText().toString().trim();
        if (!Spassword.isEmpty()) {
            return true;
        } else {
            util.displayToast(this, getResources().getString(R.string.enter_pwd), 2);
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

    @Override
    public void ShowProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void StopProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showResult(boolean sucess, String message) {
        if (sucess) {
            util.displayToast(this, message, 1);
            finish();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

        } else {
            util.displayToast(this, message, 2);
        }
    }
}