package com.xsusenet.ip4.UI.Register;

import android.util.Log;

import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.Models.ModelReg;
import com.xsusenet.ip4.Util.ApiService;
import com.xsusenet.ip4.Util.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class RegisterPresenterImpl implements RegisterContract.presenter{

    RegisterContract.view view;
    ApiService apiService;


    @Inject
    public RegisterPresenterImpl(ApiService apiService, RegisterContract.view view) {
        this.apiService = apiService;
        this.view = view;
    }

    public void callRegister(String semail, String spassword, String sfirstname, String slastname) {
        view.ShowProgress();
        HashMap<String, String> params = new HashMap<>();

        params.put("name", sfirstname);
        params.put("email", semail);
//        params.put("password", spassword);
//        params.put("c_password", spassword);
//        params.put("user_email", semail);
//        params.put("user_password", spassword);
//        params.put("first_name", sfirstname);
//        params.put("last_name", slastname);
apiService.register(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ResponseBody>() {
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
                    view.showResult(true, "Registration Successful");
                    MyPreferenceManager.getInstance().savePref(Constants.TOKEN, token);

                }
                else if (jsonObject.has("result")) {
                    JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                    int status = jsonObject1.getInt("status");
                    String msg = jsonObject1.getString("message");
                    if (status == 0)
                        view.showResult(false, msg);
                }
                else {
                    view.showResult(false, "Registration Failed");
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
        view.showResult(false, "Registration Failed");
    }
});


/*
        apiService.register(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ModelReg>() {
            @Override
            public void onSuccess(ModelReg modelReg) {
                view.StopProgress();
                if (modelReg.getResult() != null) {

                    int value = modelReg.getResult().getValue();
                    if (value == 1) {
                        String message = modelReg.getResult().getMessage();
                        String userid = modelReg.getResult().getUserId();
                        MyPreferenceManager.getInstance().savePref(Constants.USER_ID, userid);
                        view.showResult(true, message);
                    } else {
                        String message = modelReg.getResult().getMessage();
                        view.showResult(false, message);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                view.StopProgress();
            }
        });
*/
    }
}