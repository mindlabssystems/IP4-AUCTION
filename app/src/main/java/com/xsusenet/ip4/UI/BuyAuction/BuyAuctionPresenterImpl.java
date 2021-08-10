package com.xsusenet.ip4.UI.BuyAuction;

import android.util.Log;

import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.Models.Detail.ModelDetail;
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

public class BuyAuctionPresenterImpl implements BuyAuctionContract.presenter {
    ApiService apiService;
    BuyAuctionContract.view view;

    public BuyAuctionPresenterImpl(ApiService apiService, BuyAuctionContract.view view) {
        this.apiService = apiService;
        this.view = view;
    }

    @Override
    public void getDetail(String listId) {
        view.ShowProgress();
        HashMap<String, String> params = new HashMap<>();
        params.put("id", listId);
        params.put("user_id", MyPreferenceManager.getInstance().getPref(Constants.USER_ID));

        apiService.getDetails(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ModelDetail>() {

            @Override
            public void onSuccess(ModelDetail modelDetail) {
                view.StopProgress();
                if (modelDetail != null)
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
    public void makeAnOffer(String bidAmount, String listId) {
        view.ShowProgress();
        HashMap<String, String> params = new HashMap<>();
        params.put("list_id", listId);
//        params.put("user_id", MyPreferenceManager.getInstance().getPref(Constants.USER_ID));
        params.put("bid_amount", bidAmount);
        String token = "Bearer " + MyPreferenceManager.getInstance().getPref(Constants.TOKEN);
        apiService.subMitBid(token, params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ResponseBody>() {

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
                view.showResult(false, "Submission Failed");

            }
        });
    }


    @Override
    public void addToWatchList(String listId) {
        view.ShowProgress();

        HashMap<String, String> params = new HashMap<>();
        params.put("list_id", listId);
//        params.put("user_id", MyPreferenceManager.getInstance().getPref(Constants.USER_ID));
        String token = "Bearer " + MyPreferenceManager.getInstance().getPref(Constants.TOKEN);
        apiService.addToWatchList(token, params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ResponseBody>() {
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

    public void getServerCDT() {
        apiService.getCurrentTime().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                if (responseBody != null) {
                    String data = null;
                    try {
                        data = responseBody.string();
                        JSONObject jsonObject = new JSONObject(data);
                        String time = jsonObject.getString("current_date_time");
                        view.SetTime(time);

                    } catch (IOException | JSONException e) {
                        view.SetTime("");
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d("TAG", e.getMessage());
            }
        });
    }


}
