package com.xsusenet.ip4.UI.SellProduct;

import android.util.Log;

import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.Models.BlockSize.ModelBlockSize;
import com.xsusenet.ip4.Models.Regions.ModelRegions;
import com.xsusenet.ip4.Util.ApiService;
import com.xsusenet.ip4.Util.Constants;

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

public class AddProductPresenterImpl implements AddProductContract.presenter {
    ApiService apiService;
    AddProductContract.view view;

    public AddProductPresenterImpl(ApiService apiService, AddProductContract.view view) {
        this.apiService = apiService;
        this.view = view;
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

    public void Submit(String size_id, String sAddress, String region_id, String auction_or_sell, String sale_or_rent, String sPrice, String sMinterm, String sDate, String sStartIP, String sEndIP, String sInfo, String sNote, List<String> regionIntList) {

//        user_id:3
//        sale_or_rent:1
//        sale_method:2
//rent_min_term:
//        sales_type_id:1
//        size_id:1
//        region_id:1
//        end_date:2020-04-23
//        sale_price:6000
//opening_bid:
//        no_of_address:256
//        starting_ip:161.38.208.0
//        ending_ip:161.38.215.255
//        detail_info_link:https://whois.arin.net/rest/net/
//additional_note:

        view.ShowProgress();
        HashMap<String, String> params = new HashMap<>();
//        params.put("user_id", MyPreferenceManager.getInstance().getPref(Constants.USER_ID));
        params.put("sale_or_rent", sale_or_rent);
        params.put("sale_method", auction_or_sell);
        if (!sMinterm.isEmpty())
            params.put("rent_min_term", sMinterm);
        params.put("sales_type_id", "1");
        params.put("size_id", size_id);
        params.put("region_id", region_id);

//        params.put("end_date", sDate);
        if (auction_or_sell.equals("2"))
            params.put("sale_price", sPrice);
        else
            params.put("opening_bid", sPrice);
//        params.put("no_of_address", sAddress);
        params.put("starting_ip", sStartIP);
        params.put("ending_ip", "");
//        params.put("detail_info_link", sInfo);
//        if (sNote != null)
//            if (!sNote.isEmpty())
//                params.put("additional_note", sNote);
        String token = "Bearer " + MyPreferenceManager.getInstance().getPref(Constants.TOKEN);

        apiService.addProduct((ArrayList<String>) regionIntList, token, params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ResponseBody>() {

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
                            view.ShowResult(value == 1, jsonArray.getString("message"));
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
                view.ShowResult(false, "Failed");
            }
        });

    }
}
