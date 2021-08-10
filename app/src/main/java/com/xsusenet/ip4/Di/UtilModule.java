package com.xsusenet.ip4.Di;

import android.app.Application;

import com.xsusenet.ip4.Util.Util;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilModule {

    @Singleton
    @Provides
    Util provideUtil(Application application)
    {
        return new Util(application);
    }
}