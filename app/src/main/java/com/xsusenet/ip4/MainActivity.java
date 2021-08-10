package com.xsusenet.ip4;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.squareup.otto.Subscribe;
import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.NAVIGATIONDRAWER.FragmentDrawer;
import com.xsusenet.ip4.NAVIGATIONDRAWER.NavigationDrawerCallbacks;
import com.xsusenet.ip4.UI.Landing.LandingActivity;
import com.xsusenet.ip4.UI.SellProduct.AddProductActivity;
import com.xsusenet.ip4.Util.BusProvider;
import com.xsusenet.ip4.Util.Constants;
import com.xsusenet.ip4.Util.MessageData;
import com.xsusenet.ip4.Util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

import static android.view.View.VISIBLE;


public class MainActivity extends DaggerAppCompatActivity implements NavigationDrawerCallbacks, View.OnClickListener {
    NavHostFragment navHostFragment;
    NavController navController;
    @BindView(R.id.menu_icon)
    AppCompatImageView menuIcon;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.add_icon)
    AppCompatImageView addIcon;
    @BindView(R.id.notification_icon)
    AppCompatImageView notificationIcon;
    @BindView(R.id.notification_badge_main)
    AppCompatImageView notificationBadgeMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        if(getIntent().getExtras() != null){
            if(getIntent().getExtras().containsKey("position")){
               int position = getIntent().getExtras().getInt("position");
               if (position == 5){
                   navController.getGraph().setStartDestination(R.id.notifications);
                   navController.navigate(R.id.notifications, null, new NavOptions.Builder()
                           .setPopUpTo(R.id.salesList, true)
                           .build());
                   addIcon.setVisibility(View.GONE);
                   notificationBadgeMain.setVisibility(View.GONE);
                   BusProvider.getInstance().post(new MessageData("HIDE" + "," + ""));
               }
            }
        }
        setClicks();
    }

    @Subscribe
    public void onEvent(MessageData messageData) {
        String[] seperatedString = messageData.getMsgData().split(",");

        if (seperatedString[0].equalsIgnoreCase(Constants.MESSAGERECEIEVED)) {


            runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    // Stuff that updates the UI
                    if (notificationBadgeMain != null) {
                        notificationBadgeMain.setVisibility(VISIBLE);
                    }
                }
            });


        }
        if (seperatedString[0].equalsIgnoreCase(Constants.MESSAGEOPENED)) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    // Stuff that updates the UI
                    if (notificationBadgeMain != null) {
                        notificationBadgeMain.setVisibility(VISIBLE);
                    }
                }
            });

        }
    }

    private void setClicks() {
        menuIcon.setOnClickListener(this::onClick);
        notificationIcon.setOnClickListener(this::onClick);
        addIcon.setOnClickListener(this::onClick);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        drawerLayout.closeDrawer(Gravity.RIGHT);
        gotoActivity(position);
    }

    private void gotoActivity(int position) {
        if (position == R.id.relative_my_account) {
            navController.navigate(R.id.myAccount, null, new NavOptions.Builder().setLaunchSingleTop(true).build());
            addIcon.setVisibility(View.GONE);
        }
        if (position == R.id.relative_my_list) {
            navController.navigate(R.id.mySalesList, null, new NavOptions.Builder().setLaunchSingleTop(true).build());
            addIcon.setVisibility(View.VISIBLE);
        }
        if (position == R.id.relative_list) {
            navController.navigate(R.id.salesList, null, new NavOptions.Builder().setLaunchSingleTop(true).build());
            addIcon.setVisibility(View.GONE);
        }
        if (position == R.id.relative_watch_list) {
            navController.navigate(R.id.watchList, null, new NavOptions.Builder().setLaunchSingleTop(true).build());
            addIcon.setVisibility(View.GONE);
        }

        if (position == R.id.relative_notification) {
            navController.navigate(R.id.notifications, null, new NavOptions.Builder().setLaunchSingleTop(true).build());
            addIcon.setVisibility(View.GONE);
            notificationBadgeMain.setVisibility(View.GONE);
            BusProvider.getInstance().post(new MessageData("HIDE" + "," + ""));
        }
        if (position == R.id.logout) {
            startActivity(new Intent(MainActivity.this, LandingActivity.class));
            Util.getUtils().displayToast(MainActivity.this,"Logged Out Successfully",1);
            finish();
            MyPreferenceManager.getInstance().savePref(Constants.IS_LOGINED, false);
        }
        if (position == R.id.relative_my_purchases) {
            navController.navigate(R.id.my_purchases, null, new NavOptions.Builder().setLaunchSingleTop(true).build());
            addIcon.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.menu_icon) {
            drawerLayout.openDrawer(Gravity.RIGHT);
        }
        if (v.getId() == R.id.notification_icon) {
            navController.navigate(R.id.notifications, null, new NavOptions.Builder().setLaunchSingleTop(true).build());
            addIcon.setVisibility(View.GONE);
            notificationBadgeMain.setVisibility(View.GONE);
            BusProvider.getInstance().post(new MessageData("HIDE" + "," + ""));
        }
        if (v.getId() == R.id.add_icon) {
            startActivity(new Intent(MainActivity.this, AddProductActivity.class));
        }
    }

    @Override
    protected void onResume() {
        BusProvider.getInstance().register(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        BusProvider.getInstance().unregister(this);
        super.onPause();
    }

    @Override
    public void onBackPressed() {
//        if (Navigation.findNavController(this, R.id.nav_host_fragment)
//                .getCurrentDestination().getId() == R.id.salesList) {
//            super.onBackPressed();
//        } else {
        Exit().show();
//        }
    }

    private AlertDialog Exit() {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle(getResources().getString(R.string.exit))
                .setMessage(getResources().getString(R.string.exit_app))
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;
    }
}