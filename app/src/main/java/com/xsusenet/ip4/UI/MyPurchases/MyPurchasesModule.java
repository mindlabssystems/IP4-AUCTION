package com.xsusenet.ip4.UI.MyPurchases;


import com.xsusenet.ip4.Util.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class MyPurchasesModule {
    @Provides
    MyPurchasesContract.view provideView(MyPurchasesFragment myPurchasesFragment) {
        return myPurchasesFragment;
    }

    @Provides
    MyPurchasesPresenterImpl providePresenter(ApiService apiService, MyPurchasesContract.view view) {
        return new MyPurchasesPresenterImpl(apiService, view);
    }
}
