package com.xsusenet.ip4.UI.MyAccount;

import com.xsusenet.ip4.UI.EditProfile.EditProfileActivity;
import com.xsusenet.ip4.UI.EditProfile.EditProfileContract;
import com.xsusenet.ip4.UI.EditProfile.EditProfilePresenterImpl;
import com.xsusenet.ip4.Util.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class MyAccountModule {
    @Provides
    MyAccountContract.view provideView(MyAccountFragment myAccountFragment) {
        return myAccountFragment;
    }

    @Provides
    MyAccountPresenterImpl providePresenter(ApiService apiService, MyAccountContract.view view) {
        return new MyAccountPresenterImpl(apiService, view);
    }
}
