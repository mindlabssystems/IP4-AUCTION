package com.xsusenet.ip4.UI.AllSales;

import android.util.Log;

import com.xsusenet.ip4.Models.BlockSize.ModelBlockSize;
import com.xsusenet.ip4.Models.Regions.ModelRegions;
import com.xsusenet.ip4.Models.Sales.ModelSales;
import com.xsusenet.ip4.Models.SortBy.ModelSortBy;
import com.xsusenet.ip4.Util.ApiService;
import com.xsusenet.ip4.Util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class SalesListPresenterImpl implements SalesListFragmentContract.presenter {

    SalesListFragmentContract.view view;
    ApiService apiService;

    @Inject
    public SalesListPresenterImpl(ApiService apiService, SalesListFragmentContract.view view) {
        this.apiService = apiService;
        this.view = view;
    }


    public void getLists(String PURCHASE_OR_RENT, String sortBy, ArrayList<String> sizeIntList, ArrayList<String> regionIntList, ArrayList<String> salesTypeIntList, int page_no) {
        view.ShowProgress();
        String sale_or_rent;

        if (PURCHASE_OR_RENT.equals(Constants.PURCHASE))
            sale_or_rent = "1";
        else
            sale_or_rent = "2";
        apiService.getList(sizeIntList, regionIntList, salesTypeIntList, sortBy, sale_or_rent, String.valueOf(page_no)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ModelSales>() {

            @Override
            public void onSuccess(ModelSales modelSales) {
                view.StopProgress();
                if (modelSales.getResults().getIsLastpage() != null) {
                    int islstpage = modelSales.getResults().getIsLastpage();
                    view.showResult(islstpage != 0, modelSales.getResults().getAll());
                }
                else {
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
                view.SetTime("");
                Log.d("TAG", e.getMessage());
            }
        });
    }

}
