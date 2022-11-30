package com.example.tocotoco.network;

import com.example.tocotoco.model.CartInfoResult;
import com.example.tocotoco.model.CategoriesResult;
import com.example.tocotoco.model.DataTestResult;
import com.example.tocotoco.model.FavoriteProductsResult;
import com.example.tocotoco.model.LoginResult;
import com.example.tocotoco.model.ProductResult;
import com.example.tocotoco.model.ProductsByCategoryResult;
import com.example.tocotoco.model.ProductsResult;
import com.example.tocotoco.model.ProductsSessionResult;
import com.example.tocotoco.model.RegisterResult;
import com.example.tocotoco.model.SessionIdResult;
import com.example.tocotoco.model.UserCurrentResult;
import com.example.tocotoco.model.UserInfoResult;
import com.example.tocotoco.model.UserOrderResult;

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
                                    @Field("type") String type,
                                    @Field("token_device") String token_device);

    @FormUrlEncoded
    @POST("signup")
    Call<RegisterResult> registerAcc(@Field("name") String name,
                                     @Field("username") String username,
                                     @Field("password") String password,
                                     @Field("email") String email,
                                     @Field("phoneNumber") String phoneNumber);

    @FormUrlEncoded
    @POST("shopping_session/get_session_id")
    Call<SessionIdResult> getUserShoppingSession(@Field("token") String token);

    @FormUrlEncoded
    @POST("shopping_session/create_session")
    Call<RegisterResult> createShoppingSession(@Field("token") String token);

    @FormUrlEncoded
    @POST("user_info")
    Call<UserInfoResult> getUserInfo(@Field("token") String token);

    @FormUrlEncoded
    @POST("shopping_session/get_cart_info")
    Call<CartInfoResult> getCartInfo(@Field("token") String token,
                                     @Field("sessionId") int sessionId);

    @FormUrlEncoded
    @POST("update_user_address")
    Call<RegisterResult> updateUserAddress(@Field("token") String token,
                                           @Field("address") String address,
                                           @Field("phoneNumber") String phoneNumber);

    @FormUrlEncoded
    @POST("shopping_session/items")
    Call<ProductsSessionResult> itemsInShoppingSession(@Field("token") String token,
                                                       @Field("sessionId") int sessionId);
    @FormUrlEncoded
    @POST("shopping_session/delete_item")
    Call<RegisterResult> deleteItemInShoppingSession(@Field("token") String token,
                                                     @Field("itemId") int itemId,
                                                       @Field("sessionId") int sessionId);
    @FormUrlEncoded
    @POST("fav/add")
    Call<RegisterResult> addFavItem(@Field("token") String token,
                                    @Field("productId") int productId);
    @FormUrlEncoded
    @POST("fav/delete")
    Call<RegisterResult> deleteFavItem(@Field("token") String token,
                                    @Field("productId") int productId);

    @FormUrlEncoded
    @POST("order/get_user_orders")
    Call<UserOrderResult> getUserOrder(@Field("token") String token);


    @FormUrlEncoded
    @POST("user/current-order")
    Call<UserCurrentResult> getUserCurrentOrder(@Field("token") String token);

    @FormUrlEncoded
    @POST("order/get_items")
    Call<ProductsResult> getItemsInOrder(@Field("token") String token,
                                            @Field("orderId") int orderId);

    @FormUrlEncoded
    @POST("shopping_session/add_item")
    Call<RegisterResult> addItemToShoppingSession(@Field("token") String token,
                                     @Field("sessionId") int sessionId,
                                     @Field("productId") int productId,
                                     @Field("quantity") int quantity,
                                     @Field("size") String size);

    @FormUrlEncoded
    @POST("order/confirm_order")
    Call<RegisterResult> confirmOrder(@Field("token") String token,
                                                  @Field("sessionId") int sessionId,
                                                  @Field("provider") String provider,
                                                  @Field("phoneNumber") String phoneNumber,
                                                  @Field("address") String address,
                                                @Field("note") String note);
}
