package com.xsusenet.ip4.UI.SellAuction;


import com.xsusenet.ip4.Util.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class SellAuctionActivityModule {
    @Provides
    SellAuctionActivityContract.view provideView(SellAuctionActivity sellAuctionActivity) {
        return sellAuctionActivity;
    }

    @Provides
    SellAuctionActivityPresenterImpl providePresenter(ApiService apiService, SellAuctionActivityContract.view view) {
        return new SellAuctionActivityPresenterImpl(apiService, view);
    }
}
