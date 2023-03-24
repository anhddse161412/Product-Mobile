package com.mquan.productmarket.api;

import com.mquan.productmarket.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface CategoryService {
    CategoryService categoryService = new Retrofit.Builder()
            .baseUrl("https://640996b56ecd4f9e18b50c23.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CategoryService.class);



    @GET("api/v1/category")
    Call<List<Category>> getListCategory();
}
