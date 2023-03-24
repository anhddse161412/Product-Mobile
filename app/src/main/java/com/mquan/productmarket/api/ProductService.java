package com.mquan.productmarket.api;

import com.mquan.productmarket.model.Product;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductService {

    ProductService productService = new Retrofit.Builder()
            .baseUrl("https://640996b56ecd4f9e18b50c23.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductService.class);

    @GET("api/v1/products")
    Call<List<Product>> getListProduct(@Query("modelYear")int modelYear);

    @GET("api/v1/products/{id}")
    Call<Product> getProductDetail(@Path("id") String productId);

    @GET("api/v1/category/{id}/product")
    Call<List<Product>> getListProductWithCate(@Path("id") UUID categoryId);
}
