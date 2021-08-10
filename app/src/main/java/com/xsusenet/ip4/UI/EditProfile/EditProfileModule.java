package com.xsusenet.ip4.UI.EditProfile;

import com.xsusenet.ip4.UI.Login.LoginActivity;
import com.xsusenet.ip4.UI.Login.LoginContract;
import com.xsusenet.ip4.UI.Login.LoginPresenterImpl;
import com.xsusenet.ip4.Util.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class EditProfileModule {
    @Provides
    EditProfileContract.view provideView(EditProfileActivity editProfileActivity) {
        return editProfileActivity;
    }

    @Provides
    EditProfilePresenterImpl providePresenter(ApiService apiService, EditProfileContract.view view) {
        return new EditProfilePresenterImpl(apiService, view);
    }
}