package com.example.tocotoco.network;

import com.example.tocotoco.model.CityResult;
import com.example.tocotoco.model.PhuongResult;
import com.example.tocotoco.model.QuanResult;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AddressPublicAPI {
    AddressPublicAPI API_SERVICE = new Retrofit.Builder()
                .baseUrl("https://vn-public-apis.fpo.vn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AddressPublicAPI.class);


        //danh sach tinh, thanh pho
        @GET("provinces/getAll?")
        Call<CityResult> getListTinh(@Query("limit") String limit);

        //danh sach quan, huyen
        @GET("districts/getByProvince??")
        Call<CityResult> getListQuan(@Query("provinceCode") String provinceCode,
                                     @Query("limit") String limit);

        //danh sach phuong, xa
        @GET("wards/getAll???")
        Call<CityResult> getListPhuong(@Query("q") String q,
                                       @Query("cols") String cols,
                                         @Query("limit") String limit);
}
