package com.xsusenet.ip4.UI.WatchList;

import com.xsusenet.ip4.UI.MySales.MySalesContract;
import com.xsusenet.ip4.UI.MySales.MySalesFragPresenterImpl;
import com.xsusenet.ip4.UI.MySales.MySalesFragment;
import com.xsusenet.ip4.Util.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class WatchListModule {
    @Provides
    WatchListContract.view provideView(WatchListFragment mySalesFragment) {
        return mySalesFragment;
    }

    @Provides
    WatchListPresenterImpl providePresenter(ApiService apiService, WatchListContract.view view) {
        return new WatchListPresenterImpl(apiService, view);
    }
}
