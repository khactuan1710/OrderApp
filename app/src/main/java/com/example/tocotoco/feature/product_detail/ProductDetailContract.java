package com.example.tocotoco.feature.product_detail;

import com.example.tocotoco.feature.login.LoginContract;
import com.example.tocotoco.model.LoginResult;
import com.example.tocotoco.model.ProductResult;
import com.example.tocotoco.model.ProductsSessionResult;
import com.example.tocotoco.model.RegisterResult;
import com.example.tocotoco.model.SessionIdResult;
import com.example.tocotoco.network.TCCCallback;
import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import retrofit2.Response;

public interface ProductDetailContract {
    interface Interactor extends IInteractor<Presenter> {
        void getProductDetail(TCCCallback<ProductResult> callback, int id);
        void getUserShoppingSession(TCCCallback<SessionIdResult> callback, String token);
        void createShoppingSession(TCCCallback<RegisterResult> callback, String token);
        void itemsInShoppingSession(TCCCallback<ProductsSessionResult> callback, String token, int sessionId);
        void addItemToShoppingSession(TCCCallback<RegisterResult> callback, String token, int sessionId, int productId, int quantity, String size);
        void addFavItem(TCCCallback<RegisterResult> callback, String token, int productId);
        void deleteFavItem(TCCCallback<RegisterResult> callback, String token, int productId);
        void deleteItemInShoppingSession(TCCCallback<RegisterResult> callback, String token, int itemId,  int sessionId);
    }

    interface View extends PresentView<Presenter> {
        void initViewDetail(Response<ProductResult> data);
        void receiveSession(SessionIdResult.SessionId idSession);
        void addItemToCartSuccess(boolean isSuccess);
        void updateQuantityCart(Response<ProductsSessionResult> response, boolean isUpdate);
    }

    interface Presenter extends IPresenter<View, Interactor> {
        void getProductDetail(int id);
        void getUserShoppingSession(String token);
        void createShoppingSession(String token);
        void itemsInShoppingSession(String token, SessionIdResult.SessionId sessionId, boolean isUpdate);
        void addItemToShoppingSession(String token, int sessionId, int productId, int quantity, String size);
        void addFavItem(String token, int productId);
        void deleteFavItem(String token, int productId);
        void deleteItemInShoppingSession(String token, int itemId,  int sessionId);
    }
}
