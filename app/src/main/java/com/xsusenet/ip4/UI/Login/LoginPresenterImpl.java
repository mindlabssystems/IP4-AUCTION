package com.xsusenet.ip4.UI.Login;

import android.util.Log;

//import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OSDeviceState;
import com.onesignal.OneSignal;
import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.Models.LoginResult;
import com.xsusenet.ip4.Models.ModelLogin;

import com.xsusenet.ip4.Util.ApiService;
import com.xsusenet.ip4.Util.Constants;
import com.xsusenet.ip4.Util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class LoginPresenterImpl implements LoginContract.presenter {
    LoginContract.view view;
    ApiService apiService;


    public LoginPresenterImpl(ApiService apiService, LoginContract.view view) {
        this.apiService = apiService;
        this.view = view;
    }

    public void Login(String email, String password) {
        String player_id;
        view.ShowProgress();
        HashMap<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        player_id = MyPreferenceManager.getInstance().getPref(Constants.PLAYER_ID);
        if (player_id != null && !player_id.equals(""))
            params.put("player_id", player_id);
        else {
            OSDeviceState device = OneSignal.getDeviceState();
            player_id = device.getUserId();

            params.put("player_id", player_id);

        }
        apiService.login(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                view.StopProgress();
                if (responseBody != null) {
                    String data = null;
                    try {
                        data = responseBody.string();
                        JSONObject jsonObject = new JSONObject(data);
                        if (jsonObject.has("success")) {
                            JSONObject jsonObject1 = jsonObject.getJSONObject("success");
                            String token = jsonObject1.getString("token");
                            String u_id = jsonObject1.getString("id");
                            view.showResult(true, "");
                            MyPreferenceManager.getInstance().savePref(Constants.TOKEN, token);
                            MyPreferenceManager.getInstance().savePref(Constants.USER_ID, u_id);
                            MyPreferenceManager.getInstance().savePref(Constants.IS_LOGINED, true);
                        } else if (jsonObject.has("result")) {
                            JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                            int status = jsonObject1.getInt("status");
                            String msg = jsonObject1.getString("message");

                            if (status == 0) {
                                if (jsonObject1.has("flag"))
                                    view.showResult(false, msg, "show_resend");
                                else
                                    view.showResult(false, msg);
                            }

                        } else {
                            view.showResult(false, "Login Failed");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onError(Throwable e) {
                view.StopProgress();
                view.showResult(false, "Login Failed");
            }
        });


    }

    public void callforgotPwd(String email) {
        view.ShowProgress();
        HashMap<String, String> params = new HashMap<>();
        params.put("email", email);

        apiService.forgotPwd(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                if (responseBody != null) {
                    view.StopProgress();
                    try {

                        String data = responseBody.string();
                        JSONObject jsonObject = new JSONObject(data);


                        JSONObject jsonObject1 = jsonObject.getJSONObject("result");

                        if (jsonObject1.has("status")) {
                            int value = jsonObject1.getInt("status");
                            view.ForgotPwdResult(value == 1, jsonObject1.getString("message"));
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
                view.ForgotPwdResult(false, "Failed");

            }
        });

    }
    public void callResendMail(String email) {
        view.ShowProgress();
        HashMap<String, String> params = new HashMap<>();
        params.put("email", email);

        apiService.resendMail(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                if (responseBody != null) {
                    view.StopProgress();
                    try {

                        String data = responseBody.string();
                        JSONObject jsonObject = new JSONObject(data);


                        JSONObject jsonObject1 = jsonObject.getJSONObject("result");

                        if (jsonObject1.has("status")) {
                            int value = jsonObject1.getInt("status");
                            view.ResendMailResult(value == 1, jsonObject1.getString("message"));
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
                view.ResendMailResult(false, "Failed");

            }
        });

    }
}
