package com.xsusenet.ip4.UI.Sell;

import androidx.annotation.NonNull;

import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.Models.Detail.ModelDetail;
import com.xsusenet.ip4.Util.ApiService;
import com.xsusenet.ip4.Util.Constants;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SellActivityPresenterImpl implements SellActivityContract.presenter {

    ApiService apiService;
    SellActivityContract.view view;

    public SellActivityPresenterImpl(ApiService apiService, SellActivityContract.view view) {
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
            public void onSuccess(@NonNull ModelDetail modelDetail) {
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