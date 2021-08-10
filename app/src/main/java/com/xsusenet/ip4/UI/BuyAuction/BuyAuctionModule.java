package com.xsusenet.ip4.UI.BuyAuction;


import com.xsusenet.ip4.UI.Buy.BuyActivity;
import com.xsusenet.ip4.UI.Buy.BuyActivityContract;
import com.xsusenet.ip4.UI.Buy.BuyActivityPresenterImpl;
import com.xsusenet.ip4.Util.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class BuyAuctionModule {

    @Provides
    BuyAuctionContract.view provideView(BuyAuctionActivity buyAuctionActivity) {
        return buyAuctionActivity;
    }

    @Provides
    BuyAuctionPresenterImpl providePresenter(ApiService apiService, BuyAuctionContract.view view) {
        return new BuyAuctionPresenterImpl(apiService, view);
    }
}
