package com.example.tocotoco.network;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;

import com.example.tocotoco.domain.DomainSwitcher;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.CertificatePinner;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.TlsVersion;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.android.BuildConfig;

public class NetWorkController {
    private NetWorkController() {
    }
    public static Gson getGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }


    // TODO : Dong sua test api
    private static TCCAPI getAPIBuilder() {
        return DomainSwitcher.getInstance().getApiClient(TCCAPI.class);
    }


    public static void getListDataTest(TCCCallback<DataTestResult> callback, String token, String contractId, String billCycle) {
        Call<DataTestResult> call = getAPIBuilder().getListDataTest(token, contractId, billCycle);
        call.enqueue(callback);
    }
    public static void getListCategories(TCCCallback<CategoriesResult> callback) {
        Call<CategoriesResult> call = getAPIBuilder().getListCategories();
        call.enqueue(callback);
    }
    public static void getListCategories(TCCCallback<CategoriesResult> callback, int id) {
        Call<CategoriesResult> call = getAPIBuilder().getListCategories(id);
        call.enqueue(callback);
    }

    public static void getProductById(TCCCallback<ProductResult> callback, int id) {
        Call<ProductResult> call = getAPIBuilder().getProductById(id);
        call.enqueue(callback);
    }
    public static void getListProduct(TCCCallback<ProductsResult> callback) {
        Call<ProductsResult> call = getAPIBuilder().getListProduct("");
        call.enqueue(callback);
    }
    public static void getListProductByCategory(TCCCallback<ProductsByCategoryResult> callback, int id) {
        Call<ProductsByCategoryResult> call = getAPIBuilder().getListProductByCategory(id);
        call.enqueue(callback);
    }
    public static void getListProductByFavorite(TCCCallback<FavoriteProductsResult> callback, String token) {
        Call<FavoriteProductsResult> call = getAPIBuilder().getUserFavoriteItems(token);
        call.enqueue(callback);
    }
    public static void loginWithPass(TCCCallback<LoginResult> callback, String username, String pass, String type, String token_device) {
        Call<LoginResult> call = getAPIBuilder().loginWithPass(username, pass, type, token_device);
        call.enqueue(callback);
    }

    public static void registerAcc(TCCCallback<RegisterResult> callback, String name, String username, String password, String email, String phoneNumber) {
        Call<RegisterResult> call = getAPIBuilder().registerAcc(name, username, password, email, phoneNumber);
        call.enqueue(callback);
    }


    public static void getUserShoppingSession(TCCCallback<SessionIdResult> callback, String token) {
        Call<SessionIdResult> call = getAPIBuilder().getUserShoppingSession(token);
        call.enqueue(callback);
    }
    public static void createShoppingSession(TCCCallback<RegisterResult> callback, String token) {
        Call<RegisterResult> call = getAPIBuilder().createShoppingSession(token);
        call.enqueue(callback);
    }

    public static void getUserInfo(TCCCallback<UserInfoResult> callback, String token) {
        Call<UserInfoResult> call = getAPIBuilder().getUserInfo(token);
        call.enqueue(callback);
    }

    public static void itemsInShoppingSession(TCCCallback<ProductsSessionResult> callback, String token, int sessionId) {
        Call<ProductsSessionResult> call = getAPIBuilder().itemsInShoppingSession(token, sessionId);
        call.enqueue(callback);
    }

    public static void deleteItemInShoppingSession(TCCCallback<RegisterResult> callback, String token, int itemId, int sessionId) {
        Call<RegisterResult> call = getAPIBuilder().deleteItemInShoppingSession(token, itemId, sessionId);
        call.enqueue(callback);
    }

    public static void addFavItem(TCCCallback<RegisterResult> callback, String token, int sessionId) {
        Call<RegisterResult> call = getAPIBuilder().addFavItem(token, sessionId);
        call.enqueue(callback);
    }

    public static void deleteFavItem(TCCCallback<RegisterResult> callback, String token, int sessionId) {
        Call<RegisterResult> call = getAPIBuilder().deleteFavItem(token, sessionId);
        call.enqueue(callback);
    }

    public static void getItemsInOrder(TCCCallback<ProductsResult> callback, String token, int orderId) {
        Call<ProductsResult> call = getAPIBuilder().getItemsInOrder(token, orderId);
        call.enqueue(callback);
    }

    public static void getUserOrder(TCCCallback<UserOrderResult> callback, String token) {
        Call<UserOrderResult> call = getAPIBuilder().getUserOrder(token);
        call.enqueue(callback);
    }

    public static void getUserCurrentOrder(TCCCallback<UserCurrentResult> callback, String token) {
        Call<UserCurrentResult> call = getAPIBuilder().getUserCurrentOrder(token);
        call.enqueue(callback);
    }

    public static void updateUserAddress(TCCCallback<RegisterResult> callback, String token, String address, String phoneNumber) {
        Call<RegisterResult> call = getAPIBuilder().updateUserAddress(token, address, phoneNumber);
        call.enqueue(callback);
    }

    public static void getCartInfo(TCCCallback<CartInfoResult> callback, String token, int sessionId) {
        Call<CartInfoResult> call = getAPIBuilder().getCartInfo(token, sessionId);
        call.enqueue(callback);
    }

    public static void addItemToShoppingSession(TCCCallback<RegisterResult> callback, String token, int sessionId, int productId, int quantity, String size) {
        Call<RegisterResult> call = getAPIBuilder().addItemToShoppingSession(token, sessionId, productId, quantity, size);
        call.enqueue(callback);
    }

    public static void confirmOrder(TCCCallback<RegisterResult> callback, String token, int sessionId, String provider, String phoneNumber, String address, String note) {
        Call<RegisterResult> call = getAPIBuilder().confirmOrder(token, sessionId, provider, phoneNumber, address, note);
        call.enqueue(callback);
    }
}
