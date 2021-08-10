package com.xsusenet.ip4.UI.Notifications;

import com.xsusenet.ip4.UI.MySales.MySalesContract;
import com.xsusenet.ip4.UI.MySales.MySalesFragPresenterImpl;
import com.xsusenet.ip4.UI.MySales.MySalesFragment;
import com.xsusenet.ip4.Util.ApiService;

import dagger.Module;
import dagger.Provides;


@Module
public class NotificationsModule {

    @Provides
    NotificationsContract.view provideView(NotificationsFragment notificationsFragment) {
        return notificationsFragment;
    }

    @Provides
    NotificationsPresenterImpl providePresenter(ApiService apiService, NotificationsContract.view view) {
        return new NotificationsPresenterImpl(apiService, view);
    }
}
