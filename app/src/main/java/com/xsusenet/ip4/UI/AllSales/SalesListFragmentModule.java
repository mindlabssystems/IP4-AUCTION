package com.xsusenet.ip4.UI.AllSales;

import com.xsusenet.ip4.Util.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class SalesListFragmentModule {
    @Provides
    SalesListFragmentContract.view provideView(SalesListFragment salesListFragment) {
        return salesListFragment;
    }

    @Provides
    SalesListPresenterImpl providePresenter(ApiService apiService, SalesListFragmentContract.view view) {
        return new SalesListPresenterImpl(apiService, view);
    }
}
