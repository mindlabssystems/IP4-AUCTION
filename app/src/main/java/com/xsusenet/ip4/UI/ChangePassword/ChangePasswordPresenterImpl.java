package com.xsusenet.ip4.UI.ChangePassword;

import com.xsusenet.ip4.Di.MyPreferenceManager;
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

public class ChangePasswordPresenterImpl {

    ChangePasswordContract.view view;
    ApiService apiService;


    public ChangePasswordPresenterImpl(ApiService apiService, ChangePasswordContract.view view) {
        this.apiService = apiService;
        this.view = view;
    }

    public void changePassword(String spassword) {
        view.ShowProgress();
        HashMap<String, String> params = new HashMap<>();
        params.put("new_password",spassword);
//        params.put("user_id", MyPreferenceManager.getInstance().getPref(Constants.USER_ID));
        String token = "Bearer " + MyPreferenceManager.getInstance().getPref(Constants.TOKEN);


        apiService.updateProfile(token,params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ResponseBody>() {

            @Override
            public void onSuccess(ResponseBody responseBody) {
                if (responseBody != null) {
                    view.StopProgress();
                    try {

                        String data = responseBody.string();
                        JSONObject jsonObject = new JSONObject(data);

                        JSONObject jsonArray = jsonObject.getJSONObject("result");


                        if (jsonArray.has("status")) {
                            int  value = jsonArray.getInt("status");
                            view.showResult(value == 1, jsonArray.getString("message"));
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
                view.showResult(false, "Update failed");

            }
        });
    }
}
