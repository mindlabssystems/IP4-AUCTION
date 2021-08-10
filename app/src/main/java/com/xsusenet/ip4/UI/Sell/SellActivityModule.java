package com.xsusenet.ip4.UI.Sell;

import com.xsusenet.ip4.Util.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class SellActivityModule {
    @Provides
    SellActivityContract.view provideView(SellActivity sellActivity) {
        return sellActivity;
    }

    @Provides
    SellActivityPresenterImpl providePresenter(ApiService apiService, SellActivityContract.view view) {
        return new SellActivityPresenterImpl(apiService, view);
    }
}
