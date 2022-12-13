package com.example.tocotoco.home.orderdetail.network;

import com.example.tocotoco.model.OrderDetail;
import com.example.tocotoco.model.OrderItemDetail;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    ApiService API_SERVICE = new Retrofit.Builder()
            .baseUrl("https://tocotea.software/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService.class);

    @FormUrlEncoded
    @POST("order/get_order_detail")
    Call<OrderDetail> getOrderDetail(@Field("token") String token, @Field("orderId") int orderId);

    @FormUrlEncoded
    @POST("order/get_items")
    Call<OrderItemDetail> getOrderItemDetail(@Field("token") String token, @Field("orderId") int orderId);
}
