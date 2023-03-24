package com.mquan.productmarket.api;

import com.mquan.productmarket.model.Cart;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CartService {
    CartService cartService = new Retrofit.Builder()
            .baseUrl("https://640996b56ecd4f9e18b50c23.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CartService.class);

    @GET("api/v1/cart/{id}")
    Call<Cart> getCartWithId(@Path("id")String cartId);

    @POST("api/v1/cart")
    Call<Cart> createCart(@Body Cart cart);

    @PUT("api/v1/cart/{id}")
    Call<Cart> updateCart(@Path("id")String id, @Body Cart cart);

    @DELETE("api/v1/cart/{id}")
    Call<Cart> deleteCart(@Path("id")String id);
}
