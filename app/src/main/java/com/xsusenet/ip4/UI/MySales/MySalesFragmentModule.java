package com.xsusenet.ip4.UI.MySales;

import com.xsusenet.ip4.UI.EditProfile.EditProfileActivity;
import com.xsusenet.ip4.UI.EditProfile.EditProfileContract;
import com.xsusenet.ip4.UI.EditProfile.EditProfilePresenterImpl;
import com.xsusenet.ip4.Util.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class MySalesFragmentModule {
    @Provides
    MySalesContract.view provideView(MySalesFragment mySalesFragment) {
        return mySalesFragment;
    }

    @Provides
    MySalesFragPresenterImpl providePresenter(ApiService apiService, MySalesContract.view view) {
        return new MySalesFragPresenterImpl(apiService, view);
    }
}
