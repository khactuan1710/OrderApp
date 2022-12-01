package com.example.tocotoco.feature.orderStatus;

import com.example.tocotoco.feature.registerAcc.RegisterAccountContract;
import com.example.tocotoco.model.ProductResult;
import com.example.tocotoco.model.ProductsResult;
import com.example.tocotoco.model.RegisterResult;
import com.example.tocotoco.model.SessionIdResult;
import com.example.tocotoco.model.UserCurrentResult;
import com.example.tocotoco.network.TCCCallback;
import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import retrofit2.Response;

public interface OrderStatusContract {
    interface Interactor extends IInteractor<OrderStatusContract.Presenter> {
        void getUserCurrentOrder(TCCCallback<UserCurrentResult> callback, String token);
        void getItemsInOrder(TCCCallback<ProductsResult> callback, String token, int orderId);
    }

    interface View extends PresentView<OrderStatusContract.Presenter> {
        void getUserCurrentOrderSuccess(UserCurrentResult userCurrentResult);
        void getItemsInOrderSuccess(ProductsResult productsResult);
        void updateUIShipping(String shipping);
        void finishOrder(String finishOrder);
    }

    interface Presenter extends IPresenter<OrderStatusContract.View, OrderStatusContract.Interactor> {
        void getUserCurrentOrder(String token);
        void getItemsInOrder(String token, int orderId);
        void shipping(String shipping);
        void finishOrder(String finishOrder);
    }
}
