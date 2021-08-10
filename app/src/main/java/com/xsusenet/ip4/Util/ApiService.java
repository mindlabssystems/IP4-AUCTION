package com.xsusenet.ip4.Util;

import com.xsusenet.ip4.Models.Bids.ModelBids;
import com.xsusenet.ip4.Models.BlockSize.ModelBlockSize;
import com.xsusenet.ip4.Models.Country.Countries;
import com.xsusenet.ip4.Models.Detail.ModelDetail;
import com.xsusenet.ip4.Models.MyBids.MyBids;
import com.xsusenet.ip4.Models.Notifications.ModelNotification;
import com.xsusenet.ip4.Models.Purchases.Purchases;
import com.xsusenet.ip4.Models.Regions.ModelRegions;
import com.xsusenet.ip4.Models.Sales.ModelSales;
import com.xsusenet.ip4.Models.User.ModelUser;
import com.xsusenet.ip4.Models.WatchList.ModelWatchList;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Completable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

//    MyPreferenceManager MANAGER = new MyPreferenceManager(ApiService.this).getPref("TOKEN");

    @FormUrlEncoded
//    @Headers("Oakey:ipv4api22344")
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @POST(Urls.REGISTRATION)
    Single<ResponseBody> register(@FieldMap HashMap<String, String> params);


    @FormUrlEncoded
//    @Headers("Oakey:ipv4api22344")
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @POST(Urls.LOGIN)
    Single<ResponseBody> login(@FieldMap HashMap<String, String> params);

//    Single<ModelLogin> login(@FieldMap HashMap<String, String> params);


    @FormUrlEncoded
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @POST(Urls.FORGOT_PWD)
    Single<ResponseBody> forgotPwd(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @POST(Urls.RESEND_MAIL)
    Single<ResponseBody> resendMail(@FieldMap HashMap<String, String> params);


    @FormUrlEncoded
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @POST(Urls.UPDATE_PROFILE)
    Single<ResponseBody> updateProfile(@Header ("Authorization") String token,@FieldMap HashMap<String, String> params);


    //    @Headers("Oakey:ipv4api22344")
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @GET(Urls.GET_COUNTRIES)
    Single<Countries> getCountries();

    //    @Headers("Oakey:ipv4api22344")
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @GET(Urls.GET_BLOCK_SIZE)
    Single<ModelBlockSize> getBlockSize();

    //    @Headers("Oakey:ipv4api22344")
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @GET(Urls.GET_REGIONS)
    Single<ModelRegions> getRegions();

    //    @Headers("Oakey:ipv4api22344")
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @GET(Urls.GET_SORT_BY)
    Single<ResponseBody> getSortBy();


    @FormUrlEncoded
//    @Headers("Oakey:ipv4api22344")
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @POST(Urls.GET_MY_LIST)
//    Single<ModelSales> getList1(@Field("size_ids[]") ArrayList<String> size_ids, @Field("region_ids[]") ArrayList<String> region_ids, @Field("sales_type_ids[]") ArrayList<String> sales_type
//            , @Field("sort_by") String sort_by, @Field("user_id") String user_id, @Field("sale_or_rent") String sale_or_rent, @Field("page_no") String pageno);
    Single<ModelSales> getList1(@Header ("Authorization") String token,@Field("size_ids[]") ArrayList<String> size_ids, @Field("region_ids[]") ArrayList<String> region_ids, @Field("sales_type_ids[]") ArrayList<String> sales_type
            , @Field("sort_by") String sort_by, @Field("sale_or_rent") String sale_or_rent, @Field("page_no") String pageno);


    @FormUrlEncoded
//    @Headers("Oakey:ipv4api22344")
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @POST(Urls.GET_LIST)
    Single<ModelSales> getList(@Field("size_ids[]") ArrayList<String> size_ids, @Field("region_ids[]") ArrayList<String> region_ids,
                               @Field("sales_type_ids[]") ArrayList<String> sales_type
            , @Field("sort_by") String sort_by, @Field("sale_or_rent") String sale_or_rent, @Field("page_no") String pageno);

    @FormUrlEncoded
//    @Headers("Oakey:ipv4api22344")
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @POST(Urls.LIST_DETAILS)
    Single<ModelDetail> getDetails(@FieldMap HashMap<String, String> params);


//    @Headers("Oakey:ipv4api22344")
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
//    @Headers({"Authorization:Bearer " +, "Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W"})
    @POST(Urls.PROFILE)
    Single<ModelUser> getProfile(@Header ("Authorization") String token);

    @FormUrlEncoded
//    @Headers("Oakey:ipv4api22344")
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @POST(Urls.SUBMIT_BID)
    Single<ResponseBody> subMitBid(@Header("Authorization") String token,@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
//    @Headers("Oakey:ipv4api22344")
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @POST(Urls.GET_BIDS_LIST)
    Single<ModelBids> getBidsList(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
//    @Headers("Oakey:ipv4api22344")
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @POST(Urls.ADD_TO_WATCH_LIST)
    Single<ResponseBody> addToWatchList(@Header ("Authorization") String token,@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
//    @Headers("Oakey:ipv4api22344")
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @POST(Urls.REMOVE_FROM_WATCH_LIST)
    Single<ResponseBody> removeFromWatchList(@Header ("Authorization") String token,@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
//    @Headers("Oakey:ipv4api22344")
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @POST(Urls.GET_WATCH_LIST)
    Single<ModelWatchList> getWatchList(@Header ("Authorization") String token,@FieldMap HashMap<String, String> params);


    @FormUrlEncoded
//    @Headers("Oakey:ipv4api22344")
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @POST(Urls.MY_PURCHASES)
    Single<Purchases> getMyPurchases(@Header ("Authorization") String token, @FieldMap HashMap<String, String> params);

    @FormUrlEncoded
//    @Headers("Oakey:ipv4api22344")
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")

    @POST(Urls.NOTIFICATIONS)
    Single<ModelNotification> getNotifications(@Header ("Authorization") String token,@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
//    @Headers("Oakey:ipv4api22344")
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @POST(Urls.ADD_PRODUCT)
    Single<ResponseBody> addProduct(@Field("transfer_to[]") ArrayList<String> region_ids,@Header ("Authorization") String token,@FieldMap HashMap<String, String> params);


    @FormUrlEncoded
//    @Headers("Oakey:ipv4api22344")
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @POST(Urls.APP_PURCHASE)
    Single<ResponseBody> appPurchase(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
//    @Headers("Oakey:ipv4api22344")
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @POST(Urls.GET_MY_BIDS)
    Single<MyBids> getMyBids(@Header ("Authorization") String token, @FieldMap HashMap<String, String> params);


    @FormUrlEncoded
//    @Headers("Oakey:ipv4api22344")
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @POST(Urls.PUSH_STATUS)
    Single<ResponseBody> toggleNotification(@Header ("Authorization") String token, @FieldMap HashMap<String, String> params);

    @FormUrlEncoded
//    @Headers("Oakey:ipv4api22344")
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @POST(Urls.EMAIL_STATUS)
    Single<ResponseBody> toggleNotificationEmail(@Header ("Authorization") String token, @FieldMap HashMap<String, String> params);

    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @GET(Urls.GET_SERVER_TIME)
    Single<ResponseBody> getCurrentTime();

    @FormUrlEncoded
    @Headers("Appkey:N8e1WhJ42OmkYJStLX4TEvzk0EblN38W")
    @POST(Urls.CANCEL_SUBSCRIPTION)
    Single<ResponseBody> cancelSubscription(@FieldMap HashMap<String, String> params);
}