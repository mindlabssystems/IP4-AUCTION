package com.xsusenet.ip4.app;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.onesignal.OSDeviceState;
import com.onesignal.OSNotification;
import com.onesignal.OSNotificationAction;
//import com.onesignal.OSNotificationOpenResult;
//import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OSNotificationOpenedResult;
import com.onesignal.OneSignal;
import com.xsusenet.ip4.Di.AppComponent;
import com.xsusenet.ip4.Di.DaggerAppComponent;
import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.MainActivity;
import com.xsusenet.ip4.R;
import com.xsusenet.ip4.UI.Landing.LandingActivity;
import com.xsusenet.ip4.Util.BusProvider;
import com.xsusenet.ip4.Util.Constants;
import com.xsusenet.ip4.Util.MessageData;
import com.xsusenet.ip4.Util.Util;



import com.onesignal.OSNotification;
import com.onesignal.OSMutableNotification;
import com.onesignal.OSNotificationReceivedEvent;
import com.onesignal.OneSignal.OSRemoteNotificationReceivedHandler;

import org.json.JSONObject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class IP4Auction extends DaggerApplication {
    private static final String TAG = "IP4Auction";
    static IP4Auction instance;
    private static Context context;
    private static final String ONESIGNAL_APP_ID = "b4db9ec1-2884-4d69-aac3-ad88eb46e6fb";

    public static IP4Auction getInstance() {
        return instance;
    }

    public static Context getContext() {
        return context;
    }
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        new Util(getApplicationContext());
        instance = this;
        new MyPreferenceManager(this);
        FirebaseApp.initializeApp(this);


        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
//        OneSignal.unsubscribeWhenNotificationsAreDisabled(false);
        OneSignal.setNotificationOpenedHandler(new ExampleNotificationOpenedHandler());
        OSDeviceState device = OneSignal.getDeviceState();
        String player_id = device.getUserId();
        MyPreferenceManager.getInstance().savePref(Constants.PLAYER_ID, player_id);
//        OneSignal.startInit(this)
//                .autoPromptLocation(false) // default call promptLocation later
//                .setNotificationReceivedHandler(new ExampleNotificationReceivedHandler())
//                .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
//                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
//                .unsubscribeWhenNotificationsAreDisabled(true)
//                .init();

//        OSPermissionSubscriptionState status = OneSignal.getPermissionSubscriptionState();
//        String player_id = status.getSubscriptionStatus().getUserId();
//        MyPreferenceManager.getInstance().savePref(Constants.PLAYER_ID, player_id);
    }




    class ExampleNotificationOpenedHandler implements OneSignal.OSNotificationOpenedHandler {
        // This fires when a notification is opened by tapping on it.
        @Override
        public void notificationOpened(OSNotificationOpenedResult result) {
            // Printing out the full OSNotification object to the logcat for easier debugging.
            Log.i("OSNotification", "result.notification.toJSONObject(): " + result.getNotification().toJSONObject());

            JSONObject data = result.getNotification().getAdditionalData();
            if (data != null) {
                String customKey = data.optString("customkey", null);
                if (customKey != null) {
                    Log.i("OneSignalExample", "customkey set with value: " + customKey);
                }
            }

            OSNotificationAction.ActionType actionType = result.getAction().getType();
            if (actionType == OSNotificationAction.ActionType.ActionTaken) {
                Log.i("OneSignalExample", "Button pressed with id: " + result.getAction().getActionId());
            }
            try {
                BusProvider.getInstance().post(new MessageData(Constants.MESSAGEOPENED + "," + "hi"));
            } catch (Exception e) {

            }
            boolean isloggedin = MyPreferenceManager.getInstance().getPref(Constants.IS_LOGINED);
            if (isloggedin) {
                String customKey;
                String openURL = null;
                Object activityToLaunch = MainActivity.class;
                Intent intent = new Intent(getApplicationContext(), (Class<?>) activityToLaunch);
//         // intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("position", 5);
                startActivity(intent);

                if (data != null) {
                    customKey = data.optString("customkey", null);
                    openURL = data.optString("openURL", null);

                    if (customKey != null)
                        Log.i("OneSignalExample", "customkey set with value: " + customKey);

                    if (openURL != null)
                        Log.i("OneSignalExample", "openURL to webview with URL value: " + openURL);
                }

                if (actionType == OSNotificationAction.ActionType.ActionTaken) {
                    Log.i("OneSignalExample", "Button pressed with id: " + result.getAction().getActionId());
                    if (result.getAction().getActionId().equals("id1")) {
                        Log.i("OneSignalExample", "button id called: " + result.getAction().getActionId());
//               activityToLaunch = GreenActivity.class;
                    } else {
                        Log.i("OneSignalExample", "button id called: " + result.getAction().getActionId());
                    }

                }
            } else {
                Object activityToLaunch = LandingActivity.class;
                Intent intent = new Intent(getApplicationContext(), (Class<?>) activityToLaunch);
//         // intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("position", 5);
                startActivity(intent);
            }
            // The following can be used to open an Activity of your choice.
            // Replace - getApplicationContext() - with any Android Context.
            // Intent intent = new Intent(getApplicationContext(), YourActivity.class);
            // intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
            // startActivity(intent);

            // Add the following to your AndroidManifest.xml to prevent the launching of your main Activity
            //   if you are calling startActivity above.
     /*
        <application ...>
          <meta-data android:name="com.onesignal.NotificationOpened.DEFAULT" android:value="DISABLE" />
        </application>
     */
        }
    }

/*
    private  class ExampleNotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {
        @Override
        public void notificationReceived(OSNotification notification) {
            JSONObject data = notification.payload.additionalData;
            Log.e("Help", data.toString());
            BusProvider.getInstance().post(Constants.MESSAGERECEIEVED + "," + "");


        }

    }
*/

/*
    private static class ExampleNotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {
        @Override
        public void notificationReceived(OSNotification notification) {
            JSONObject data = notification.payload.additionalData;
            String notificationID = notification.payload.notificationID;
//            String title = notification.payload.title;
//            String body = notification.payload.body;
//            String smallIcon = notification.payload.smallIcon;
//            String largeIcon = notification.payload.largeIcon;
//            String bigPicture = notification.payload.bigPicture;
//            String smallIconAccentColor = notification.payload.smallIconAccentColor;
//            String sound = notification.payload.sound;
//            String ledColor = notification.payload.ledColor;
//            int lockScreenVisibility = notification.payload.lockScreenVisibility;
//            String groupKey = notification.payload.groupKey;
//            String groupMessage = notification.payload.groupMessage;
//            String fromProjectNumber = notification.payload.fromProjectNumber;
//            String rawPayload = notification.payload.rawPayload;

            String customKey;

            Log.i("OneSignalExample", "NotificationID received: " + notificationID);

            if (data != null) {
                customKey = data.optString("customkey", null);
                if (customKey != null)
                    Log.i("OneSignalExample", "customkey set with value: " + customKey);
            }
            BusProvider.getInstance().post(Constants.MESSAGERECEIEVED + "," + "");

        }
    }
*/

/*
    private class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
        // This fires when a notification is opened by tapping on it.
        @Override
        public void notificationOpened(OSNotificationOpenResult result) {
            OSNotificationAction.ActionType actionType = result.action.type;
            JSONObject data = result.notification.payload.additionalData;
            String launchUrl = result.notification.payload.launchURL; // update docs launchUrl
            try {
                BusProvider.getInstance().post(Constants.MESSAGEOPENED + "," + "hi");
            } catch (Exception e) {

            }
            boolean isloggedin = MyPreferenceManager.getInstance().getPref(Constants.IS_LOGINED);
            if (isloggedin) {
                String customKey;
                String openURL = null;
                Object activityToLaunch = MainActivity.class;
                Intent intent = new Intent(getApplicationContext(), (Class<?>) activityToLaunch);
//         // intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("position", 5);
                startActivity(intent);

                if (data != null) {
                    customKey = data.optString("customkey", null);
                    openURL = data.optString("openURL", null);

                    if (customKey != null)
                        Log.i("OneSignalExample", "customkey set with value: " + customKey);

                    if (openURL != null)
                        Log.i("OneSignalExample", "openURL to webview with URL value: " + openURL);
                }

                if (actionType == OSNotificationAction.ActionType.ActionTaken) {
                    Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);
                    if (result.action.actionID.equals("id1")) {
                        Log.i("OneSignalExample", "button id called: " + result.action.actionID);
//               activityToLaunch = GreenActivity.class;
                    } else {
                        Log.i("OneSignalExample", "button id called: " + result.action.actionID);
                    }

                }
            } else {
                Object activityToLaunch = LandingActivity.class;
                Intent intent = new Intent(getApplicationContext(), (Class<?>) activityToLaunch);
//         // intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("position", 5);
                startActivity(intent);
            }

        }
    }
*/

}