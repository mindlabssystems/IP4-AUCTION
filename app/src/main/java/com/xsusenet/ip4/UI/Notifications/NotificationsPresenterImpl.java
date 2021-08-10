package com.xsusenet.ip4.UI.Notifications;

import com.xsusenet.ip4.Di.MyPreferenceManager;
import com.xsusenet.ip4.Models.Notifications.ModelNotification;
import com.xsusenet.ip4.Util.ApiService;
import com.xsusenet.ip4.Util.Constants;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class NotificationsPresenterImpl implements NotificationsContract.presenter {

    ApiService apiService;
    NotificationsContract.view view;

    public NotificationsPresenterImpl(ApiService apiService, NotificationsContract.view view) {
        this.apiService = apiService;
        this.view = view;
    }

    public void getNotifications(int page_no) {
        view.ShowProgress();
        HashMap<String, String> params = new HashMap<>();
        params.put("page_no", String.valueOf(page_no));
//        params.put("user_id", MyPreferenceManager.getInstance().getPref(Constants.USER_ID));
        String token = "Bearer " + MyPreferenceManager.getInstance().getPref(Constants.TOKEN);

        apiService.getNotifications(token, params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<ModelNotification>() {

            @Override
            public void onSuccess(ModelNotification modelNotification) {
                view.StopProgress();
                if (modelNotification != null) {
                    if (modelNotification.getResults().getIsLastpage() != null) {
                        int islstpage = modelNotification.getResults().getIsLastpage();
                        view.showResult(islstpage != 0, modelNotification.getResults().getAll());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
