package com.example.tocotoco.domain;

import android.content.Context;

import com.example.tocotoco.util.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.BuildConfig;

public class DomainSwitcher {
    private static final DomainSwitcher INSTANCE = new DomainSwitcher();
    public static DomainSwitcher getInstance() {
        return INSTANCE;
    }
    private HashMap<String, Object> mApiClientProvider = new HashMap<>();
    public <T> T getApiClient(Class<T> apiClientClass) {
        String activeDomain = getActiveDomain();
        return getApiClient(activeDomain, apiClientClass);
    }
    private String getActiveDomain() {
        return "https://apivtp.vietteltelecom.vn:6768/myviettel.php/";
    }

    public <T> T getApiClient(String baseUrl, Class<T> apiClientClass) {

        String key = getKey(baseUrl, apiClientClass);
        T apiClient = (T) mApiClientProvider.get(key);

        if (apiClient == null) {

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .registerTypeAdapter(Long.class, new Utils.LongTypeAdapter())
                    .registerTypeAdapter(Integer.class, new Utils.IntegerTypeAdapter())
                    .registerTypeAdapter(Double.class, new Utils.DoubleTypeAdapter())
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient())
                    .build();

            apiClient = retrofit.create(apiClientClass);
            mApiClientProvider.put(key, apiClient);
        }
        return apiClient;
    }

    private OkHttpClient okHttpClient() {

        final String deviceName = android.os.Build.MODEL;
        final String versionApp = BuildConfig.VERSION_NAME;
        final String build_code = String.valueOf(BuildConfig.VERSION_CODE);

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            }};

            OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder()
                    //.certificatePinner(getCertificatePinner()) sửa theo yêu cầu
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();
                            HttpUrl httpUrl = original.url();
                            HttpUrl newHttpUrl = httpUrl.newBuilder().addQueryParameter("device_name", deviceName)
                                    .addQueryParameter("version_app", versionApp)
                                    .addQueryParameter("build_code", build_code)
                                    .addQueryParameter("os_type", "android")
                                    .addQueryParameter("os_version", android.os.Build.VERSION.SDK_INT + "")
                                    .build();
                            Request.Builder requestBuilder = original.newBuilder().url(newHttpUrl);
                            Request request = requestBuilder.build();

                            return chain.proceed(request);
                        }
                    })
                    .retryOnConnectionFailure(false)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS);

            if (BuildConfig.DEBUG) {
            }

            return clientBuilder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private <T> String getKey(String baseUrl, Class<T> apiClientClass) {
        return baseUrl + apiClientClass.getName();
    }

}
