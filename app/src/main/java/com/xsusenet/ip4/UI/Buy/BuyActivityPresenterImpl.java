package com.xsusenet.ip4.UI.Buy;

import android.util.Log;

import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.Models.Detail.ModelDetail;
import com.xsusenet.ip4.Models.User.ModelUser;
import com.xsusenet.ip4.Util.ApiService;
import com.xsusenet.ip4.Util.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class BuyActivityPresenterImpl implements BuyActivityContract.presenter{

    ApiService apiService;
    BuyActivityContract.view view;

    public BuyActivityPresenterImpl(ApiService apiService, BuyActivityContract.view view) {
        this.apiService = apiService;
        this.view = view;
    }

    @Override
    public void getDetail(String listId){
        view.ShowProgress();
        HashMap<String, String> params = new HashMap<>();
        params.put("id", listId);
        params.put("user_id", MyPreferenceManager.getInstance().getPref(Constants.USER_ID));
        apiService.getDetails(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ModelDetail>() {
            @Override
            public void onSuccess(ModelDetail modelDetail) {
                view.StopProgress();
                if(modelDetail != null)
                view.setList(modelDetail.getResults().getList());
            }

            @Override
            public void onError(Throwable e) {
                view.StopProgress();
                view.setList(null);

            }
        });
    }


    @Override
    public void addToWatchList(String listId) {
        view.ShowProgress();
        HashMap<String,String> params =new HashMap<>();
        params.put("list_id", listId);
//        params.put("user_id", MyPreferenceManager.getInstance().getPref(Constants.USER_ID));
        String token = "Bearer " + MyPreferenceManager.getInstance().getPref(Constants.TOKEN);

        apiService.addToWatchList(token,params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ResponseBody>() {
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
                            view.WatchResult(value == 1, jsonArray.getString("message"));
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
                view.WatchResult(false, "Add to watchlist failed");

            }
        });


    }

    public void removeFromWatchList(String listId) {

        view.ShowProgress();
        HashMap<String,String> params =new HashMap<>();
        params.put("list_id", listId);
//        params.put("user_id", MyPreferenceManager.getInstance().getPref(Constants.USER_ID));
        String token = "Bearer " + MyPreferenceManager.getInstance().getPref(Constants.TOKEN);

        apiService.removeFromWatchList(token,params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ResponseBody>() {
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
                            view.WatchRemoveResult(value == 1, jsonArray.getString("message"));
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
                view.WatchResult(false, "Remove watchlist not allowed here");

            }
        });

    }


    public void buyProduct(String listId) {
        view.ShowProgress();
        HashMap<String,String> params =new HashMap<>();
        params.put("list_id", listId);
        params.put("user_id", MyPreferenceManager.getInstance().getPref(Constants.USER_ID));
        apiService.appPurchase(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                if (responseBody != null) {
                    view.StopProgress();
/*
                    try {

                        String data = responseBody.string();
                        JSONObject jsonObject = new JSONObject(data);

                        JSONObject jsonArray = jsonObject.getJSONObject("result");


                        if (jsonArray.has("value")) {
                            int value = jsonArray.getInt("value");
//                            if (value == 1) {
//                                view.WatchResult(true, jsonArray.getString("message"));
//                            } else {
//                                view.WatchResult(false, jsonArray.getString("message"));
//                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
*/
                }

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

}