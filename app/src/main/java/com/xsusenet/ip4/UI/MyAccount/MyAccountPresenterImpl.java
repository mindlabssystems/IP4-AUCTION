package com.xsusenet.ip4.UI.MyAccount;


import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.Models.User.ModelUser;
import com.xsusenet.ip4.UI.EditProfile.EditProfileContract;
import com.xsusenet.ip4.Util.ApiService;
import com.xsusenet.ip4.Util.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class MyAccountPresenterImpl implements MyAccountContract.presenter {
    MyAccountContract.view view;
    ApiService apiService;

    public MyAccountPresenterImpl(ApiService apiService, MyAccountContract.view view) {
        this.apiService = apiService;
        this.view = view;
    }

    public void changeStatusofPush(String status) {
        view.ShowProgress();
        HashMap<String,String> params =new HashMap<>();
        params.put("push_notification_status", status);
//        params.put("email_notification_status", status);
        String token = "Bearer " + MyPreferenceManager.getInstance().getPref(Constants.TOKEN);

        apiService.toggleNotification(token,params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                if (responseBody != null) {
                    view.StopProgress();
                    try {
                        String data = responseBody.string();
                        JSONObject jsonObject = new JSONObject(data);

                        JSONObject jsonArray = jsonObject.getJSONObject("result");

                        if (jsonArray.has("status")) {
                            int value = jsonArray.getInt("status");
                            view.PushNotificationsResult(value == 1, jsonArray.getString("message"));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onError(Throwable e) {
                view.StopProgress();
                view.PushNotificationsResult(false, "Remove watchlist not allowed here");

            }
        });
    }
    public void changeStatusofEmailNotifications(String status) {
        view.ShowProgress();
        HashMap<String,String> params =new HashMap<>();
        params.put("email_notification_status", status);
        String token = "Bearer " + MyPreferenceManager.getInstance().getPref(Constants.TOKEN);

        apiService.toggleNotificationEmail(token,params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                if (responseBody != null) {
                    view.StopProgress();
                    try {
                        String data = responseBody.string();
                        JSONObject jsonObject = new JSONObject(data);

                        JSONObject jsonArray = jsonObject.getJSONObject("result");

                        if (jsonArray.has("status")) {
                            int value = jsonArray.getInt("status");
                            view.EmailNotificationsResult(value == 1, jsonArray.getString("message"));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onError(Throwable e) {
                view.StopProgress();
                view.EmailNotificationsResult(false, "Couldn't Update");

            }
        });
    }

    @Override
    public void getProfile() {

        HashMap<String, String> params = new HashMap<>();
//        params.put("user_id", MyPreferenceManager.getInstance().getPref(Constants.USER_ID));
        String token = "Bearer "+MyPreferenceManager.getInstance().getPref(Constants.TOKEN);
        apiService.getProfile(token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ModelUser>() {
            @Override
            public void onSuccess(ModelUser modelUser) {
                view.StopProgress();
                if (modelUser != null) {
                    view.setValues(modelUser.getSuccess());
                }
            }

            @Override
            public void onError(Throwable e) {

                view.setValues(null);

            }
        });
    }


}
