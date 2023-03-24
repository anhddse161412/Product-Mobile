package com.mquan.productmarket.api;

import com.mquan.productmarket.model.Store;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StoreService {
    StoreService storeService = new Retrofit.Builder()
            .baseUrl("https://640996b56ecd4f9e18b50c23.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StoreService.class);

    @GET("api/v1/stores")
    Call<List<Store>> getListStore();

    @GET("api/v1/stores/{id}")
    Call<Store> getStoreDetail(@Path("id") String storeId);
}
