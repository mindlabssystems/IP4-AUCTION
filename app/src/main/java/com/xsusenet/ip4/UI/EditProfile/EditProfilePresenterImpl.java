package com.xsusenet.ip4.UI.EditProfile;

import android.util.Log;

import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.Models.Country.Countries;

import com.xsusenet.ip4.Models.User.ModelUser;

import com.xsusenet.ip4.Util.ApiService;
import com.xsusenet.ip4.Util.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class EditProfilePresenterImpl implements EditProfileContract.presenter {
    EditProfileContract.view view;
    ApiService apiService;


    public EditProfilePresenterImpl(ApiService apiService, EditProfileContract.view view) {
        this.apiService = apiService;
        this.view = view;
    }

    @Override
    public void getCountry() {
//        HashMap<String, String> params = new HashMap<>();

        apiService.getCountries().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<Countries>() {
            @Override
            public void onSuccess(Countries countries) {
                if (countries != null) {
                    if (countries.getCountries() != null)
                        view.ListCountries(countries.getCountries());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d("TAG", e.getMessage());
            }
        });

    }

    @Override
    public void Update(String sfirstName, String slastName, String semail, String sjobTitle, String sMobile, String sAddress, String countryId, String sCity, String sPostcode, String title,String state) {
        view.ShowProgress();
        HashMap<String, String> params = new HashMap<>();
        params.put("email", semail);

        params.put("name", sfirstName);
//        params.put("first_name", sfirstName);
//        params.put("last_name", slastName);
        params.put("mobile_number", sMobile);
        params.put("business_name", sjobTitle);
        params.put("address_1", sAddress);
        params.put("city", sCity);
        params.put("state",state );
        params.put("zipcode", sPostcode);
        params.put("country_id", countryId);
        params.put("job_title", title);
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

                        JSONObject jsonObject1 = jsonObject.getJSONObject("result");


                        if (jsonObject1.has("status")) {
                            int value = jsonObject1.getInt("status");
                            view.showResult(value == 1, jsonObject1.getString("message"));
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
                view.showResult(false, "Update Failed");

            }
        });

    }


    @Override
    public void getProfile() {
        view.ShowProgress();
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
                view.StopProgress();
                view.setValues(null);

            }
        });
    }
}