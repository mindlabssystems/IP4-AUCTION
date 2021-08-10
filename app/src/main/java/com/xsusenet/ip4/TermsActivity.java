package com.xsusenet.ip4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xsusenet.ip4.UI.EditProfile.EditProfileActivity;
import com.xsusenet.ip4.Util.Util;

import java.util.HashMap;
import java.util.Map;

public class TermsActivity extends AppCompatActivity {
    Map<String, String> extraHeaders = new HashMap<>();
    WebView webView;
    TextView title;
    RelativeLayout back;
    String FROM;
    String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        if (getIntent().getExtras() != null) {
            FROM = getIntent().getStringExtra("from");
            URL = getIntent().getStringExtra("url");
        }
        setView();
        if (FROM.equals("register")) {
            title.setText("Terms and Conditions");
        } else if (FROM.equals("closing")) {
            title.setText("Closing Conditions");
        } else {
            title.setText("Leasing Conditions");
        }

        extraHeaders.put("Appkey", "N8e1WhJ42OmkYJStLX4TEvzk0EblN38W");

        webView.loadUrl(URL, extraHeaders);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(URL, extraHeaders);
                return true;
            }
        });

    }

    private void setView() {
        Util.getUtils().overrideFontsBold(TermsActivity.this, findViewById(R.id.Title));
        title = findViewById(R.id.Title);
        back = findViewById(R.id.go_back);
        webView = findViewById(R.id.webview);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        webView.setScrollbarFadingEnabled(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setInitialScale(0);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
    }

}
