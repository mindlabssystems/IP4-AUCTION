package com.xsusenet.ip4.Di;


import com.xsusenet.ip4.MainActivity;
import com.xsusenet.ip4.UI.Bids.BidListActivity;
import com.xsusenet.ip4.UI.Bids.BidsListModule;
import com.xsusenet.ip4.UI.Buy.BuyActivity;
import com.xsusenet.ip4.UI.Buy.BuyActivityModule;
import com.xsusenet.ip4.UI.BuyAuction.BuyAuctionActivity;
import com.xsusenet.ip4.UI.BuyAuction.BuyAuctionModule;
import com.xsusenet.ip4.UI.ChangePassword.ChangePasswordActivity;
import com.xsusenet.ip4.UI.ChangePassword.ChangePasswordModule;
import com.xsusenet.ip4.UI.EditProfile.EditProfileActivity;
import com.xsusenet.ip4.UI.EditProfile.EditProfileModule;
import com.xsusenet.ip4.UI.Login.LoginActivity;
import com.xsusenet.ip4.UI.Login.LoginModule;
import com.xsusenet.ip4.UI.MyBids.MyBidsActivity;
import com.xsusenet.ip4.UI.MyBids.MyBidsModule;
import com.xsusenet.ip4.UI.Register.RegisterActivity;
import com.xsusenet.ip4.UI.Register.RegisterModule;
import com.xsusenet.ip4.UI.SellAuction.SellAuctionActivity;
import com.xsusenet.ip4.UI.SellAuction.SellAuctionActivityModule;
import com.xsusenet.ip4.UI.Sell.SellActivity;
import com.xsusenet.ip4.UI.Sell.SellActivityModule;
import com.xsusenet.ip4.UI.SellProduct.AddProductActivity;
import com.xsusenet.ip4.UI.SellProduct.AddProductModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {BuyAuctionModule.class})
    abstract BuyAuctionActivity bindDetail();

    @ContributesAndroidInjector(modules = {BuyActivityModule.class})
    abstract BuyActivity bindBuyActivityDetail();


    @ContributesAndroidInjector(modules = {BidsListModule.class})
    abstract BidListActivity bindBidListActivityDetail();

    @ContributesAndroidInjector(modules = {SellActivityModule.class})
    abstract SellActivity bindSellActivityDetail();

    @ContributesAndroidInjector(modules = {AddProductModule.class})
    abstract AddProductActivity bindAddProductDetail();

    @ContributesAndroidInjector(modules = {SellAuctionActivityModule.class})
    abstract SellAuctionActivity bindSellAuctionDetail();

    @ContributesAndroidInjector(modules = {EditProfileModule.class})
    abstract EditProfileActivity bindEditProfileActivity();

    @ContributesAndroidInjector(modules = {LoginModule.class})
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = {RegisterModule.class})
    abstract RegisterActivity bindRegisterActivity();


    @ContributesAndroidInjector(modules = {ChangePasswordModule.class})
    abstract ChangePasswordActivity bindChangePasswordActivity();

    @ContributesAndroidInjector(modules = {MainActivityModule.class, FragmentProvider.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {MyBidsModule.class})
    abstract MyBidsActivity bindMyBidsActivity();
}