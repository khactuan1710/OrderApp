package com.example.tocotoco.feature.cart;

import com.example.tocotoco.feature.order.OrderContract;
import com.example.tocotoco.model.CartInfoResult;
import com.example.tocotoco.model.ProductsSessionResult;
import com.example.tocotoco.model.RegisterResult;
import com.example.tocotoco.network.NetWorkController;
import com.example.tocotoco.network.TCCCallback;
import com.gemvietnam.base.viper.Interactor;

public class CartInteractor extends Interactor<CartContract.Presenter>
        implements CartContract.Interactor{
    public CartInteractor(CartContract.Presenter presenter) {
        super(presenter);
    }

    @Override
    public void itemsInShoppingSession(TCCCallback<ProductsSessionResult> callback, String token, int sessionId) {
        NetWorkController.itemsInShoppingSession(callback, token, sessionId);
    }

    @Override
    public void getCartInfo(TCCCallback<CartInfoResult> callback, String token, int sessionId) {
        NetWorkController.getCartInfo(callback, token, sessionId);
    }

    @Override
    public void addItemToShoppingSession(TCCCallback<RegisterResult> callback, String token, int sessionId, int productId, int quantity, String size) {
        NetWorkController.addItemToShoppingSession(callback, token, sessionId, productId, quantity, size);
    }

    @Override
    public void deleteItemInShoppingSession(TCCCallback<RegisterResult> callback, String token, int itemId, int sessionId) {
        NetWorkController.deleteItemInShoppingSession(callback, token, itemId, sessionId);
    }
}
