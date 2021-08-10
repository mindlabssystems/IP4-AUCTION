package com.xsusenet.ip4.UI.Register;
import com.xsusenet.ip4.Util.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class RegisterModule {
    @Provides
    RegisterContract.view provideView(RegisterActivity registerActivity) {
        return registerActivity;
    }

    @Provides
    RegisterPresenterImpl providePresenter(ApiService apiService, RegisterContract.view view) {
        return new RegisterPresenterImpl(apiService, view);
    }
}
