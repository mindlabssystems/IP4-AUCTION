package com.xsusenet.ip4.UI.ChangePassword;

import com.xsusenet.ip4.Util.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class ChangePasswordModule {

    @Provides
    ChangePasswordContract.view provideView(ChangePasswordActivity changePasswordActivity) {
        return changePasswordActivity;
    }

    @Provides
    ChangePasswordPresenterImpl providePresenter(ApiService apiService, ChangePasswordContract.view view) {
        return new ChangePasswordPresenterImpl(apiService, view);
    }
}