package com.mquan.productmarket.api;

import com.mquan.productmarket.model.Account;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginService {
    LoginService loginService = new Retrofit.Builder()
            .baseUrl("https://640996b56ecd4f9e18b50c23.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginService.class);

    @GET("api/v1/accounts")
    Call<Account[]> getLoginAccount(@Query("email")String email);

}
