package com.xsusenet.ip4.UI.SellProduct;

import com.xsusenet.ip4.UI.EditProfile.EditProfileActivity;
import com.xsusenet.ip4.UI.EditProfile.EditProfileContract;
import com.xsusenet.ip4.UI.EditProfile.EditProfilePresenterImpl;
import com.xsusenet.ip4.Util.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class AddProductModule {

    @Provides
    AddProductContract.view provideView(AddProductActivity addProductActivity) {
        return addProductActivity;
    }

    @Provides
    AddProductPresenterImpl providePresenter(ApiService apiService, AddProductContract.view view) {
        return new AddProductPresenterImpl(apiService, view);
    }
}
