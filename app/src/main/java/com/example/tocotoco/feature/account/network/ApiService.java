package com.example.tocotoco.feature.account.network;

import com.example.tocotoco.model.AccountResult;
import com.example.tocotoco.model.CategoriesResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
    ApiService API_SERVICE = new Retrofit.Builder()
            .baseUrl("https://tocotea.software/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @FormUrlEncoded
    @POST("user_info")
    Call<AccountResult> getUserInfor(@Field("token") String token);

    @FormUrlEncoded
    @POST("update_user_info")
    Call<AccountResult> updateUserInfor(@Field("token") String token, @Field("username") String username,
                                        @Field("name") String name, @Field("email") String email, @Field("phoneNumber") String phoneNumber);
}
