package com.example.tocotoco.network;

import com.example.tocotoco.model.CategoriesResult;
import com.example.tocotoco.model.DataTestResult;
import com.example.tocotoco.model.ProductResult;
import com.example.tocotoco.model.ProductsByCategoryResult;
import com.example.tocotoco.model.ProductsResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TCCAPI {
    @FormUrlEncoded
    @POST("getDebitByContract")
    Call<DataTestResult> getListDataTest(@Field("token") String token,
                                         @Field("contractId") String provinceId,
                                         @Field("billCycle") String complainerPhone);

    @POST("product_categories")
    Call<CategoriesResult> getListCategories();

    @FormUrlEncoded
    @POST("product_category")
    Call<CategoriesResult> getListCategories(@Field("id") int id);


    @FormUrlEncoded
    @POST("product")
    Call<ProductResult> getProductById(@Field("id") int id);

    @FormUrlEncoded
    @POST("products")
    Call<ProductsResult> getListProduct(@Field("token") String token);

    @FormUrlEncoded
    @POST("get_products_by_category")
    Call<ProductsByCategoryResult> getListProductByCategory(@Field("categoryId") int id);

}
