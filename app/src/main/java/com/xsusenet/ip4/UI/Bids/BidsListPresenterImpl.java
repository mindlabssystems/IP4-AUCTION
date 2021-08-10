package com.xsusenet.ip4.UI.Bids;

import com.google.android.gms.common.api.Api;
import com.xsusenet.ip4.Models.Bids.ModelBids;
import com.xsusenet.ip4.Models.Detail.ModelDetail;
import com.xsusenet.ip4.UI.EditProfile.EditProfileActivity;
import com.xsusenet.ip4.Util.ApiService;
import com.xsusenet.ip4.Util.Util;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class BidsListPresenterImpl implements BidsListContract.presenter {
    ApiService apiService;
    BidsListContract.view view;

    public BidsListPresenterImpl(ApiService apiService, BidsListContract.view view) {
        this.apiService = apiService;
        this.view = view;
    }


    @Override
    public void getBidList(String listId,int page_no) {
        view.ShowProgress();
        HashMap<String, String> params = new HashMap<>();
        params.put("list_id", listId);
        apiService.getBidsList(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ModelBids>() {

            @Override
            public void onSuccess(ModelBids modelBids) {
                view.StopProgress();
                if(modelBids != null){
                    if(modelBids.getBids()!= null){
                        view.setList(modelBids.getBids());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                view.StopProgress();
            }
        });
    }
}
