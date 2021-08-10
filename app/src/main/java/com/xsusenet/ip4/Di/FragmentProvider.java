package com.xsusenet.ip4.Di;

import com.xsusenet.ip4.UI.MyAccount.MyAccountFragment;
import com.xsusenet.ip4.UI.MyAccount.MyAccountModule;
import com.xsusenet.ip4.UI.MyPurchases.MyPurchasesFragment;
import com.xsusenet.ip4.UI.MyPurchases.MyPurchasesModule;
import com.xsusenet.ip4.UI.MySales.MySalesFragment;
import com.xsusenet.ip4.UI.MySales.MySalesFragmentModule;
import com.xsusenet.ip4.UI.AllSales.SalesListFragment;
import com.xsusenet.ip4.UI.AllSales.SalesListFragmentModule;
import com.xsusenet.ip4.UI.Notifications.NotificationsFragment;
import com.xsusenet.ip4.UI.Notifications.NotificationsModule;
import com.xsusenet.ip4.UI.WatchList.WatchListFragment;
import com.xsusenet.ip4.UI.WatchList.WatchListModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentProvider {
    @ContributesAndroidInjector(modules = MyAccountModule.class)
    abstract MyAccountFragment myAccountDetails();

    @ContributesAndroidInjector(modules = MySalesFragmentModule.class)
    abstract MySalesFragment mySalesList();


    @ContributesAndroidInjector(modules = SalesListFragmentModule.class)
    abstract SalesListFragment mySalesListFragment();

    @ContributesAndroidInjector(modules = WatchListModule.class)
    abstract WatchListFragment myWatchListFragment();

    @ContributesAndroidInjector(modules = MyPurchasesModule.class)
    abstract MyPurchasesFragment myPurchasesFragment();

    @ContributesAndroidInjector(modules = NotificationsModule.class)
    abstract NotificationsFragment myNotificationsFragment();
}