package com.xsusenet.ip4.UI.Landing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xsusenet.ip4.R;
import com.xsusenet.ip4.UI.Login.LoginActivity;
import com.xsusenet.ip4.UI.Register.RegisterActivity;
import com.xsusenet.ip4.Util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LandingActivity extends AppCompatActivity  {

    @BindView(R.id.loginBut)
    TextView loginBut;
    @BindView(R.id.RegisterBut)
    RelativeLayout RegisterBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        ButterKnife.bind(this);

        initUi();

    }


    private void initUi() {
        Util.getUtils().overrideFontsSemiBold(LandingActivity.this, findViewById(R.id.text1));
        Util.getUtils().overrideFontsBold(LandingActivity.this, findViewById(R.id.text2));
        Util.getUtils().overrideFontsBold(LandingActivity.this, findViewById(R.id.loginBut));
        Util.getUtils().overrideFontsRegular(LandingActivity.this, findViewById(R.id.no_account));
        Util.getUtils().overrideFontsBold(LandingActivity.this, findViewById(R.id.register_text));
    }

    @OnClick(R.id.loginBut)
    void setLoginBut() {
        Intent i = new Intent(LandingActivity.this,
                LoginActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.RegisterBut)
    void setRegisterBut() {
        Intent intent = new Intent(LandingActivity.this,
                RegisterActivity.class);
        startActivity(intent);
    }

}
