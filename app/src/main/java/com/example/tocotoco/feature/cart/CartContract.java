package com.example.tocotoco.feature.cart;

import com.example.tocotoco.feature.order.OrderContract;
import com.example.tocotoco.model.CartInfoResult;
import com.example.tocotoco.model.ProductsSessionResult;
import com.example.tocotoco.model.RegisterResult;
import com.example.tocotoco.model.UserInfoResult;
import com.example.tocotoco.network.TCCCallback;
import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import retrofit2.Response;

public interface CartContract {
    interface Interactor extends IInteractor<CartContract.Presenter> {
        void itemsInShoppingSession(TCCCallback<ProductsSessionResult> callback, String token, int sessionId);
        void getCartInfo(TCCCallback<CartInfoResult> callback, String token, int sessionId);
        void addItemToShoppingSession(TCCCallback<RegisterResult> callback, String token, int sessionId, int productId, int quantity, String size);
        void deleteItemInShoppingSession(TCCCallback<RegisterResult> callback, String token, int itemId,  int sessionId);
    }

    interface View extends PresentView<CartContract.Presenter> {
        void initViewDetail(Response<ProductsSessionResult> data);
        void getCartInfoSuccess(Response<CartInfoResult> data);
        void getCartInfoFail();
    }

    interface Presenter extends IPresenter<CartContract.View, CartContract.Interactor> {
        void itemsInShoppingSession(String token, int sessionId);
        void getCartInfo(String token, int sessionId);
        void addItemToShoppingSession(String token, int sessionId, int productId, int quantity, String size);
        void deleteItemInShoppingSession(String token, int itemId,  int sessionId);
    }
}
