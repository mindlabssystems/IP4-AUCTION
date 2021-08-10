package com.xsusenet.ip4.UI.Buy;

import com.xsusenet.ip4.UI.EditProfile.EditProfileActivity;
import com.xsusenet.ip4.UI.EditProfile.EditProfileContract;
import com.xsusenet.ip4.UI.EditProfile.EditProfilePresenterImpl;
import com.xsusenet.ip4.Util.ApiService;

import dagger.Module;
import dagger.Provides;
@Module
public class BuyActivityModule {
    @Provides
    BuyActivityContract.view provideView(BuyActivity buyActivity) {
        return buyActivity;
    }

    @Provides
    BuyActivityPresenterImpl providePresenter(ApiService apiService, BuyActivityContract.view view) {
        return new BuyActivityPresenterImpl(apiService, view);
    }
}
