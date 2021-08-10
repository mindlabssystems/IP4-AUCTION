package com.xsusenet.ip4.Di;

import android.app.Application;
import android.content.Context;

import com.xsusenet.ip4.Util.ApiService;
import com.xsusenet.ip4.Util.RetrofitModule;
import com.xsusenet.ip4.app.IP4Auction;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        RetrofitModule.class,
        UtilModule.class,
        ActivityBuilder.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    @Override
    void inject(DaggerApplication instance);

    void inject(IP4Auction app);

    ApiService getApiService();

    Context getContext();


    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

}

