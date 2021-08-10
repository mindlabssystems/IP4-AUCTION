package com.xsusenet.ip4.UI.SellAuction;

import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.Models.Detail.ModelDetail;
import com.xsusenet.ip4.Util.ApiService;
import com.xsusenet.ip4.Util.Constants;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SellAuctionActivityPresenterImpl implements SellAuctionActivityContract.presenter {
    SellAuctionActivityContract.view view;
    ApiService apiService;

    public SellAuctionActivityPresenterImpl(ApiService apiService, SellAuctionActivityContract.view view) {
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

}
