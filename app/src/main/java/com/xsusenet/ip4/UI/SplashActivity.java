package com.xsusenet.ip4.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.MainActivity;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.UI.Landing.LandingActivity;
import com.xsusenet.ip4.Util.Constants;

public class SplashActivity extends AppCompatActivity {
    AppCompatImageView loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loader = (AppCompatImageView) findViewById(R.id.loader);

        RotateAnimation rotate = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setRepeatCount(Integer.MAX_VALUE);
        rotate.setDuration(1500);
        rotate.setInterpolator(new LinearInterpolator());
        loader.startAnimation(rotate);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
/*
                Intent i=new Intent(SplashActivity.this,MainActivity.class);
                //Intent is used to switch from one activity to another.

                startActivity(i);*/
                if (MyPreferenceManager.getInstance().getPref(Constants.IS_LOGINED, false)) {
                    Intent i=new Intent(SplashActivity.this,MainActivity.class);
                    //Intent is used to switch from one activity to another.

                    startActivity(i);
                    //invoke the SecondActivity.
//                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

                    finish();
                }
                else {
                    Intent i=new Intent(SplashActivity.this,
                            LandingActivity.class);
                    //Intent is used to switch from one activity to another.

                    startActivity(i);
                    //invoke the SecondActivity.
//                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

                    finish();
                }

            }
        }, 3000);
    }
}
