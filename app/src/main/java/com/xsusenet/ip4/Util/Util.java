package com.xsusenet.ip4.Util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.xsusenet.ip4.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;


public class Util {

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    Context context;
    public static final StringBuilder sb;
    private static Util instance;
    private int CDT_hr, CDT_min, CDT_sec, CDT_year, CDT_month, CDT_day;
    String strCDT, day, month, hour, second, minute;


    public static Util getUtils() {
        return instance;
    }

    public Util(Context context) {

        this.context = context;
        String prefsFile = context.getPackageName();
        sharedPreferences = context.getSharedPreferences(prefsFile, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        instance = this;
    }

    static {
        sb = new StringBuilder();
    }


    public boolean isValidEmail(CharSequence target) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public boolean isValidMobile(String phone2) {
        boolean check = false;
        if (Pattern.matches("[0-9]+", phone2)) {
            check = phone2.length() >= 10 && phone2.length() <= 13;
        } else {
            check = false;
        }
        return check;
    }

    public void displayToast(final Activity activity, final String message, final int status) {
        Toasty.Config.getInstance()
                .setErrorColor(activity.getResources().getColor(R.color.red)) // optional
                .setInfoColor(activity.getResources().getColor(R.color.blue_text))
                .setSuccessColor(activity.getResources().getColor(R.color.green))
                .apply(); // required
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (status == 1) {
                    Toasty.success(activity, message, Toast.LENGTH_SHORT, true).show();
                } else if (status == 2) {
                    Toasty.error(activity, message, Toast.LENGTH_SHORT, true).show();
                } else {
                    Toasty.info(activity, message, Toast.LENGTH_SHORT, true).show();
                }
            }
        });
    }

    public void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void overrideFontsSemiBold(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFontsSemiBold(context, child);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/SEGUISB.TTF"));
            }
        } catch (Exception e) {
        }
    }

    public void overrideFontsBold(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFontsSemiBold(context, child);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/SEGOEUIB.TTF"));
            }
        } catch (Exception e) {
        }
    }

    public void overrideFontsRegular(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFontsSemiBold(context, child);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/segoeui.ttf"));
            }
        } catch (Exception e) {
        }
    }

    public String getCDT() {
        Calendar mcurrentDateTime = Calendar.getInstance();
        CDT_hr = mcurrentDateTime.get(Calendar.HOUR_OF_DAY);
        CDT_min = mcurrentDateTime.get(Calendar.MINUTE);
        CDT_sec = mcurrentDateTime.get(Calendar.SECOND);
        CDT_year = mcurrentDateTime.get(Calendar.YEAR);
        CDT_month = mcurrentDateTime.get(Calendar.MONTH) + 1;
        CDT_day = mcurrentDateTime.get(Calendar.DAY_OF_MONTH);
        Log.d("DATE", "current d&t:" + CDT_year + "/" + CDT_month + "/" + CDT_day + " " + CDT_hr + ":" + CDT_min + ":" + CDT_sec);
        int val = ((((CDT_year % 100 * 12 + CDT_month) * 31 + CDT_day) * 24 + CDT_hr) * 60 + CDT_min) * 60 + CDT_sec;

        if (CDT_day < 10)
            day = "0" + CDT_day;
        else
            day = "" + CDT_day;
        if (CDT_month < 10)
            month = "0" + CDT_month;
        else
            month = "" + CDT_month;
        if (CDT_hr < 10)
            hour = "0" + CDT_hr;
        else
            hour = "" + CDT_hr;
        if (CDT_sec < 10)
            second = "0" + CDT_sec;
        else
            second = "" + CDT_sec;
        if (CDT_min < 10)
            minute = "0" + CDT_min;
        else
            minute = "" + CDT_min;


//        strCDT = CDT_year + "-" + month + "-" + day + "  " + hour + ":" + minute + ":" + second;
        strCDT = month + "/" + day + "/" + CDT_year + "  " + hour + ":" + minute + ":" + second;

        return strCDT;
    }

    public boolean isValidIP(String ip) {
        try {
            if (ip == null || ip.isEmpty()) {
                return false;
            }

            String[] parts = ip.split("\\.");
            if (parts.length != 4) {
                return false;
            }

            for (String s : parts) {
                int i = Integer.parseInt(s);
                if ((i < 0) || (i > 255)) {
                    return false;
                }
            }
            return !ip.endsWith(".");
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public long differenceInMinit(String d1, String d2) {
//        long diff[] = new long[4];
        long diff = 0;

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        try {
            Date currentDate = format.parse(d1);
            Date endDate = format.parse(d2);

            //in milliseconds
            long difff = endDate.getTime() - currentDate.getTime();
//            Log.d("DIFF", +endDate.getTime() +":" +currentDate.getTime());
            Log.d("DIFF", d2 + ":" + d1);
            diff = difff ;
            long diffSeconds = difff / 1000 % 60;
            long diffMinutes = difff / (60 * 1000) % 60;
            long diffHours = difff / (60 * 60 * 1000) % 24;
            long diffDays = difff / (24 * 60 * 60 * 1000);

         /*   if (diffDays > 0)
                diff[0] = diffDays;
            else {
                diff[0] = 0;
            }
            if (diffHours > 0)
                diff[1] = diffHours;
            else {
                diff[1] = 0;
            }
            if (diffMinutes > 0)
                diff[2] = diffMinutes;
            else {
                diff[2] = 0;
            }
            if (diffSeconds > 0)
                diff[3] = diffSeconds;
            else {
                diff[3] = 0;
            }*/

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return diff;
    }
}