package com.xsusenet.ip4.UI.MyBids;

import android.util.Log;

import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.Models.MyBids.MyBids;
import com.xsusenet.ip4.Models.WatchList.ModelWatchList;
import com.xsusenet.ip4.UI.WatchList.WatchListContract;
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

public class MyBidsPresenter  implements MyBidsContract.presenter{
    MyBidsContract.view view;
    ApiService apiService;

    public MyBidsPresenter(ApiService apiService, MyBidsContract.view view) {
        this.view = view;
        this.apiService =apiService;
    }

    @Override
    public void getMyBids() {
        view.ShowProgress();
        HashMap<String, String> params = new HashMap<>();
        String token = "Bearer " + MyPreferenceManager.getInstance().getPref(Constants.TOKEN);
        System.out.println(token);
        apiService.getMyBids(token, params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<MyBids>() {

            @Override
            public void onSuccess(MyBids myBids) {

                if (myBids != null) {
                    view.StopProgress();
                    view.showResult(true, myBids.getResults().getAll());
                } else {
                    view.showResult(false, null);
                }
            }
            @Override
            public void onError(Throwable e) {
                view.StopProgress();

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
