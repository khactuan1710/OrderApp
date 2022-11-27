package com.example.tocotoco.feature.order;

import com.example.tocotoco.feature.product_detail.ProductDetailContract;
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

public class OrderContract {
    interface Interactor extends IInteractor<OrderContract.Presenter> {
        void itemsInShoppingSession(TCCCallback<ProductsSessionResult> callback, String token, int sessionId);
        void getUserInfo(TCCCallback<UserInfoResult> callback, String token);
    }

    interface View extends PresentView<OrderContract.Presenter> {
        void initViewDetail(Response<ProductsSessionResult> data);
    }

    interface Presenter extends IPresenter<OrderContract.View, OrderContract.Interactor> {
        void itemsInShoppingSession(String token, SessionIdResult.SessionId sessionId, boolean isUpdate);
        void getUserInfo(String token);
    }
}
