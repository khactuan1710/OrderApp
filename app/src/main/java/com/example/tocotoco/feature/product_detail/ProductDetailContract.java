package com.example.tocotoco.feature.product_detail;

import com.example.tocotoco.feature.login.LoginContract;
import com.example.tocotoco.model.LoginResult;
import com.example.tocotoco.model.ProductResult;
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
        void addItemToShoppingSession(TCCCallback<RegisterResult> callback, String token, int sessionId, int productId, int quantity, String size);
        void addFavItem(TCCCallback<RegisterResult> callback, String token, int productId);
        void deleteFavItem(TCCCallback<RegisterResult> callback, String token, int productId);
    }

    interface View extends PresentView<Presenter> {
        void initViewDetail(Response<ProductResult> data);
    }

    interface Presenter extends IPresenter<View, Interactor> {
        void getProductDetail(int id);
        void getUserShoppingSession(String token);
        void addItemToShoppingSession(String token, int sessionId, int productId, int quantity, String size);
        void addFavItem(String token, int productId);
        void deleteFavItem(String token, int productId);
    }
}
