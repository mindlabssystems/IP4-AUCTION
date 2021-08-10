package com.xsusenet.ip4.UI.MySales;

import android.util.Log;

import com.xsusenet.ip4.Di.MyPreferenceManager;
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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class MySalesFragPresenterImpl implements MySalesContract.presenter {
    ApiService apiService;
    MySalesContract.view view;

    public MySalesFragPresenterImpl(ApiService apiService, MySalesContract.view view) {
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
                    if(modelBlockSize.getSize() != null)
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
/*
    public void getLists(String category_id, String sales_type_id, String size_id, String region_id, String sale_method, String list_id, int page_no) {
        view.ShowProgress();
        HashMap<String, String> params = new HashMap<>();
        if (category_id != null)
            params.put("category_id", category_id);
        if (sales_type_id != null)
            params.put("sales_type_id", sales_type_id);
        if (region_id != null)
            params.put("region_id", region_id);
        if (sale_method != null)
            params.put("sale_method", sale_method);
        if (list_id != null)
            params.put("list_id", list_id);
        params.put("page_no", String.valueOf(page_no));
        params.put("user_id", MyPreferenceManager.getInstance().getPref(Constants.USER_ID));


        apiService.getList(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ModelSales>() {

            @Override
            public void onSuccess(ModelSales modelSales) {
                view.StopProgress();
                if (modelSales.getIsLastpage() != null) {
                    int islstpage = modelSales.getIsLastpage();
                    if (islstpage == 0)
                        view.showResult(false, modelSales.getAll());
                    else
                        view.showResult(true, modelSales.getAll());
                }
            }

            @Override
            public void onError(Throwable e) {
                view.StopProgress();
            }
        });
    }
*/


   @Override
    public void getLists1(String SALE_OR_LEASE, String sortBy, ArrayList<String> sizeIntList, ArrayList<String> regionIntList, ArrayList<String> salesTypeIntList, int page_no) {
        view.ShowProgress();
        String sale_or_rent;

        if (SALE_OR_LEASE.equals(Constants.SALE))
            sale_or_rent = "1";
        else
            sale_or_rent = "2";

       String token = "Bearer "+MyPreferenceManager.getInstance().getPref(Constants.TOKEN);

       apiService.getList1(token,sizeIntList, regionIntList, salesTypeIntList, sortBy,sale_or_rent, String.valueOf(page_no)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ModelSales>() {

            @Override
            public void onSuccess(ModelSales modelSales) {
                view.StopProgress();
                if (modelSales.getResults().getIsLastpage() != null) {
                    int islstpage = modelSales.getResults().getIsLastpage();
                    view.showResult(islstpage != 0, modelSales.getResults().getAll());
                }else {
                    view.showResult(false,null);
                }
            }

            @Override
            public void onError(Throwable e) {
                view.StopProgress();
                view.showResult(false,null);
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