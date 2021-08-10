package com.xsusenet.ip4.UI.WatchList;

import android.util.Log;

import com.google.android.gms.common.api.Api;
import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.Models.BlockSize.ModelBlockSize;
import com.xsusenet.ip4.Models.Regions.ModelRegions;
import com.xsusenet.ip4.Models.Sales.ModelSales;
import com.xsusenet.ip4.Models.SortBy.ModelSortBy;
import com.xsusenet.ip4.Models.WatchList.ModelWatchList;
import com.xsusenet.ip4.Util.ApiService;
import com.xsusenet.ip4.Util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class WatchListPresenterImpl implements WatchListContract.presenter {

    WatchListContract.view view;
    ApiService apiService;

    public WatchListPresenterImpl(ApiService apiService, WatchListContract.view view) {
        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void getSortBy() {
        apiService.getSortBy().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ResponseBody>() {

            @Override
            public void onSuccess(ResponseBody responseBody) {
                if (responseBody != null) {
                    String data = null;
                    try {
                        data = responseBody.string();
                        JSONObject jsonObject = new JSONObject(data);
                        JSONArray jsonArray = jsonObject.getJSONArray("sortinglist");
                        List<ModelSortBy> sortByList = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonobject = jsonArray.getJSONObject(i);
                            String key = jsonobject.getString("key");
                            String value = jsonobject.getString("value");
                            ModelSortBy modelSortBy = new ModelSortBy();
                            modelSortBy.setValue(value);
                            modelSortBy.setKey(key);
                            sortByList.add(modelSortBy);
                        }
                        if (sortByList.size() > 0) {
                            view.SortByResult(sortByList);
                        }
                    } catch (IOException | JSONException e) {
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

    @Override
    public void getBlockSize() {
        apiService.getBlockSize().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ModelBlockSize>() {

            @Override
            public void onSuccess(ModelBlockSize modelBlockSize) {
                if (modelBlockSize != null)
                    if (modelBlockSize.getSize() != null)
                        view.BlockSizeResult(modelBlockSize.getSize());
            }

            @Override
            public void onError(Throwable e) {
                Log.d("TAG", e.getMessage());
            }
        });
    }

    @Override
    public void getRegions() {

        apiService.getRegions().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ModelRegions>() {

            @Override
            public void onSuccess(ModelRegions modelRegions) {
                if (modelRegions != null)
                    if (modelRegions.getRegions() != null)
                        view.RegionResult(modelRegions.getRegions());
            }

            @Override
            public void onError(Throwable e) {
                Log.d("TAG", e.getMessage());
            }
        });
    }


    @Override
    public void getWatchList() {
        view.ShowProgress();
        HashMap<String, String> params = new HashMap<>();
//        params.put("user_id", MyPreferenceManager.getInstance().getPref(Constants.USER_ID));
        String token = "Bearer " + MyPreferenceManager.getInstance().getPref(Constants.TOKEN);
        System.out.println(token);
        apiService.getWatchList(token, params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ModelWatchList>() {

            @Override
            public void onSuccess(ModelWatchList modelWatchList) {
                if (modelWatchList != null) {
                    view.StopProgress();
                    view.showResult(true, modelWatchList.getResults().getAll());
                } else {
                    view.showResult(false, null);
                }
            }

            @Override
            public void onError(Throwable e) {
                view.StopProgress();
                view.showResult(false, null);
            }
        });
    }
}
