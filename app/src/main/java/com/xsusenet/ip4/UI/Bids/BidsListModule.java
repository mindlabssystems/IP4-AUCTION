package com.xsusenet.ip4.UI.Bids;

import com.xsusenet.ip4.UI.Sell.SellActivity;
import com.xsusenet.ip4.UI.Sell.SellActivityContract;
import com.xsusenet.ip4.UI.Sell.SellActivityPresenterImpl;
import com.xsusenet.ip4.Util.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class BidsListModule {
    @Provides
    BidsListContract.view provideView(BidListActivity bidListActivity) {
        return bidListActivity;
    }

    @Provides
    BidsListPresenterImpl providePresenter(ApiService apiService, BidsListContract.view view) {
        return new BidsListPresenterImpl(apiService, view);
    }
}
