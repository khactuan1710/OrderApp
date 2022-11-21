package com.example.tocotoco.network;

import com.example.tocotoco.model.CategoriesResult;
import com.example.tocotoco.model.DataTestResult;
import com.example.tocotoco.model.FavoriteProductsResult;
import com.example.tocotoco.model.LoginResult;
import com.example.tocotoco.model.ProductResult;
import com.example.tocotoco.model.ProductsByCategoryResult;
import com.example.tocotoco.model.ProductsResult;
import com.example.tocotoco.model.RegisterResult;

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

    @FormUrlEncoded
    @POST("fav/items")
    Call<FavoriteProductsResult> getUserFavoriteItems(@Field("token") String token);

    @FormUrlEncoded
    @POST("login")
    Call<LoginResult> loginWithPass(@Field("username") String username,
                                    @Field("password") String password,
                                    @Field("type") String type);

    @FormUrlEncoded
    @POST("signup")
    Call<RegisterResult> registerAcc(@Field("name") String name,
                                     @Field("username") String username,
                                     @Field("password") String password,
                                     @Field("email") String email,
                                     @Field("phoneNumber") String phoneNumber);

}
