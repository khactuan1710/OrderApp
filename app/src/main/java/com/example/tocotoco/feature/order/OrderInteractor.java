package com.example.tocotoco.feature.order;

import com.example.tocotoco.feature.product_detail.ProductDetailContract;
import com.example.tocotoco.model.CartInfoResult;
import com.example.tocotoco.model.ProductResult;
import com.example.tocotoco.model.ProductsSessionResult;
import com.example.tocotoco.model.UserInfoResult;
import com.example.tocotoco.network.NetWorkController;
import com.example.tocotoco.network.TCCCallback;
import com.gemvietnam.base.viper.Interactor;

public class OrderInteractor extends Interactor<OrderContract.Presenter>
        implements OrderContract.Interactor{
    public OrderInteractor(OrderContract.Presenter presenter) {
        super(presenter);
    }

    @Override
    public void itemsInShoppingSession(TCCCallback<ProductsSessionResult> callback, String token, int sessionId) {
        NetWorkController.itemsInShoppingSession(callback, token, sessionId);
    }

    @Override
    public void getUserInfo(TCCCallback<UserInfoResult> callback, String token) {
        NetWorkController.getUserInfo(callback, token);
    }

    @Override
    public void getCartInfo(TCCCallback<CartInfoResult> callback, String token, int sessionId) {
        NetWorkController.getCartInfo(callback, token, sessionId);
    }
}
