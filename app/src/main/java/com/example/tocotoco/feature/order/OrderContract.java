package com.example.tocotoco.feature.order;

import com.example.tocotoco.feature.product_detail.ProductDetailContract;
import com.example.tocotoco.model.CartInfoResult;
import com.example.tocotoco.model.ProductResult;
import com.example.tocotoco.model.ProductsSessionResult;
import com.example.tocotoco.model.RegisterResult;
import com.example.tocotoco.model.SessionIdResult;
import com.example.tocotoco.model.UserInfoResult;
import com.example.tocotoco.network.TCCCallback;
import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import retrofit2.Response;

public interface OrderContract {
    interface Interactor extends IInteractor<OrderContract.Presenter> {
        void itemsInShoppingSession(TCCCallback<ProductsSessionResult> callback, String token, int sessionId);
        void getUserInfo(TCCCallback<UserInfoResult> callback, String token);
        void getCartInfo(TCCCallback<CartInfoResult> callback, String token, int sessionId);
        void confirmOrder(TCCCallback<RegisterResult> callback, String token, int sessionId, String provider, String phoneNumber, String address, String note);
    }

    interface View extends PresentView<OrderContract.Presenter> {
        void initViewDetail(Response<ProductsSessionResult> data);
        void getCartInfoSuccess(Response<CartInfoResult> data);
        void getUserInfoSuccess(Response<UserInfoResult> data);
        void confirmOrderSuccess(Response<RegisterResult> data);
    }

    interface Presenter extends IPresenter<OrderContract.View, OrderContract.Interactor> {
        void itemsInShoppingSession(String token, int sessionId);
        void getUserInfo(String token);
        void getCartInfo(String token, int sessionId);
        void confirmOrder(String token, int sessionId, String provider, String phoneNumber, String address, String note);
    }
}
