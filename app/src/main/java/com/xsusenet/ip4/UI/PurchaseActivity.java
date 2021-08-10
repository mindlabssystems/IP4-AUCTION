package com.xsusenet.ip4.UI;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.TermsActivity;
import com.xsusenet.ip4.UI.SellAuction.SellAuctionActivity;
import com.xsusenet.ip4.Util.Constants;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.concurrent.RejectedExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PurchaseActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webView;

    String list_id;
    String url = "http://ip4auction.mindmockups.com/ip4sales/app-purchase";
    @BindView(R.id.back)
    AppCompatImageView back;
    @BindView(R.id.use_full_links)
    TextView useFullLinks;

    boolean RENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        ButterKnife.bind(this);
        setView();
//        mTitle.setText("Shopping Cart");
//        relativeLayout.setVisibility(View.GONE);

        webView.setVerticalScrollBarEnabled(true);
        try {

            Bundle Extras = getIntent().getExtras();

            if (Extras != null) {
                if (Extras.getString("list_id") != null) {

                    list_id = Extras.getString("list_id");

                    String postData = "list_id=" + URLEncoder.encode(list_id, "UTF-8")
                            + "&user_id=" + URLEncoder.encode(MyPreferenceManager.getInstance().getPref(Constants.USER_ID, ""), "UTF-8");
                    Log.d("TAG", "" + Arrays.toString(postData.getBytes()));
                    webView.postUrl(url, postData.getBytes());

                    webView.setWebViewClient(new WebViewClient() {
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            view.loadUrl(url);
                            return true;
                        }
                    });

                    if (Extras.getString("type").equals("closing")) {
                        useFullLinks.setText("Closing Conditions");
                        RENT = false;
                    } else {
                        useFullLinks.setText("Leasing Conditions");
                        RENT = true;
                    }

                } else {

                    //                webView.loadUrl("https://deliveryjet.in/app/cart.php");
                    //                webView.setWebChromeClient(new WebChromeClient());
                    //                webView.setWebViewClient(new WebViewClient());
                    //
                }


            }
        } catch (Exception e) {
            Log.d("EXC", "onCreate: " + e.getMessage());

//            webView.loadUrl("https://deliveryjet.in/app/cart.php");
//            webView.setWebViewClient(new WebViewClient());
//            webView.setWebChromeClient(new WebChromeClient());
//

        }
    }

    @OnClick(R.id.use_full_links)
    void onUsefulLinks() {
        if (RENT) {
            Intent intent = new Intent(PurchaseActivity.this, TermsActivity.class);
            intent.putExtra("from", "leasing");
            intent.putExtra("url", Constants.URL_LEASING);
            startActivity(intent);
        } else {
            Intent intent1 = new Intent(PurchaseActivity.this, TermsActivity.class);
            intent1.putExtra("from", "closing");
            intent1.putExtra("url", Constants.URL_CLOSING);
            startActivity(intent1);
        }
    }

    @OnClick(R.id.back)
    void onBack() {
        onBackPressed();
    }

    private void setView() {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        webView.setScrollbarFadingEnabled(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setInitialScale(0);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setLoadWithOverviewMode(true);

        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.getSettings().setUseWideViewPort(true);
    }
}
