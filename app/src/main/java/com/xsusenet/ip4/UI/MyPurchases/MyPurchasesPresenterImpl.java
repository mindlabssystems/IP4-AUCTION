package com.xsusenet.ip4.UI.MyPurchases;

import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.Models.Purchases.Purchases;
import com.xsusenet.ip4.Models.WatchList.ModelWatchList;
import com.xsusenet.ip4.Util.ApiService;
import com.xsusenet.ip4.Util.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class MyPurchasesPresenterImpl {
    ApiService apiService;
    MyPurchasesContract.view view;

    public MyPurchasesPresenterImpl(ApiService apiService, MyPurchasesContract.view view) {
        this.apiService = apiService;
        this.view = view;
    }

    public void getMyPurchasesList(int page_no) {
        view.ShowProgress();
        HashMap<String, String> params = new HashMap<>();
        String token = "Bearer " + MyPreferenceManager.getInstance().getPref(Constants.TOKEN);
        params.put("page_no", String.valueOf(page_no));
        System.out.println(token);
        apiService.getMyPurchases(token, params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<Purchases>() {
            @Override
            public void onSuccess(@NonNull Purchases purchases) {
                view.StopProgress();
                if (purchases.getResults().getIsLastpage() != null) {
                    int islstpage = purchases.getResults().getIsLastpage();
                    view.showResult(islstpage != 0, purchases.getResults().getAll());
                } else {
                    view.showResult(false, null);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.StopProgress();
                view.showResult(false, null);
            }
        });
    }

    public void cancelSubscription(String subscriptionId) {
        view.ShowProgress();
        HashMap<String, String> params = new HashMap<>();

        params.put("transaction_id", subscriptionId);
        params.put("user_id", MyPreferenceManager.getInstance().getPref(Constants.USER_ID));

        apiService.cancelSubscription(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ResponseBody>() {
            @Override
            public void onSuccess( ResponseBody responseBody) {
                if (responseBody != null) {
                    view.StopProgress();
                    try {
                        String data = responseBody.string();
                        JSONObject jsonObject = new JSONObject(data);
                        JSONObject jsonArray = jsonObject.getJSONObject("result");
                        if (jsonArray.has("status")) {
                            int value = jsonArray.getInt("status");
                            view.CancelResult(value == 1, jsonArray.getString("message"));
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
                view.CancelResult(false, "Not possible to cancel this subscription.");
            }
        });
    }
}
