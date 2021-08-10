package com.xsusenet.ip4.UI.Login;
import com.xsusenet.ip4.UI.Register.RegisterActivity;
import com.xsusenet.ip4.UI.Register.RegisterContract;
import com.xsusenet.ip4.UI.Register.RegisterPresenterImpl;
import com.xsusenet.ip4.Util.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {
    @Provides
    LoginContract.view provideView(LoginActivity loginActivity) {
        return loginActivity;
    }

    @Provides
    LoginPresenterImpl providePresenter(ApiService apiService, LoginContract.view view) {
        return new LoginPresenterImpl(apiService, view);
    }
}
