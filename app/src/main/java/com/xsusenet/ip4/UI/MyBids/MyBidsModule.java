package com.xsusenet.ip4.UI.MyBids;

import com.xsusenet.ip4.UI.WatchList.WatchListContract;
import com.xsusenet.ip4.UI.WatchList.WatchListFragment;
import com.xsusenet.ip4.UI.WatchList.WatchListPresenterImpl;
import com.xsusenet.ip4.Util.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class MyBidsModule {

    @Provides
    MyBidsContract.view provideView(MyBidsActivity myBidsActivity) {
        return myBidsActivity;
    }

    @Provides
    MyBidsPresenter providePresenter(ApiService apiService, MyBidsContract.view view) {
        return new MyBidsPresenter(apiService, view);
    }
}
