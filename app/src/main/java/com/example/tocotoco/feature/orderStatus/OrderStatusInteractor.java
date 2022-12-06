package com.example.tocotoco.feature.orderStatus;

import com.example.tocotoco.feature.registerAcc.RegisterAccountContract;
import com.example.tocotoco.model.ProductsResult;
import com.example.tocotoco.model.UserCurrentResult;
import com.example.tocotoco.network.NetWorkController;
import com.example.tocotoco.network.TCCCallback;
import com.gemvietnam.base.viper.Interactor;

public class OrderStatusInteractor extends Interactor<OrderStatusContract.Presenter>
        implements OrderStatusContract.Interactor{

    public OrderStatusInteractor(OrderStatusContract.Presenter presenter) {
        super(presenter);
    }

    @Override
    public void getUserCurrentOrder(TCCCallback<UserCurrentResult> callback, String token) {
        NetWorkController.getUserCurrentOrder(callback, token);
    }

    @Override
    public void getItemsInOrder(TCCCallback<ProductsResult> callback, String token, int orderId) {
        NetWorkController.getItemsInOrder(callback, token, orderId);
    }
}
