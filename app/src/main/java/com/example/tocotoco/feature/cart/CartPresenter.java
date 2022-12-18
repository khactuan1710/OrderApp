package com.example.tocotoco.feature.cart;

import com.example.tocotoco.dialog.DialogUtils;
import com.example.tocotoco.feature.order.OrderContract;
import com.example.tocotoco.feature.order.OrderFragment;
import com.example.tocotoco.feature.order.OrderInteractor;
import com.example.tocotoco.model.CartInfoResult;
import com.example.tocotoco.model.ProductsSessionResult;
import com.example.tocotoco.model.RegisterResult;
import com.example.tocotoco.network.TCCCallback;
import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import retrofit2.Call;
import retrofit2.Response;

public class CartPresenter extends Presenter<CartContract.View, CartContract.Interactor>
        implements CartContract.Presenter{
    public CartPresenter(ContainerView containerView) {
        super(containerView);
    }
    @Override
    public void start() {

    }

    @Override
    public CartContract.Interactor onCreateInteractor() {
        return new CartInteractor(this);
    }

    @Override
    public CartContract.View onCreateView() {
        return CartFragment.getInstance();
    }

    @Override
    public void itemsInShoppingSession(String token, int sessionId) {
        DialogUtils.showProgressDialog(getViewContext());
        mInteractor.itemsInShoppingSession(new TCCCallback<ProductsSessionResult>() {
            @Override
            public void onTCTCSuccess(Call<ProductsSessionResult> call, Response<ProductsSessionResult> response) {
                DialogUtils.dismissProgressDialog();
                if(response.body().getIsSuccess()) {
                    mView.initViewDetail(response);
                }
            }

            @Override
            public void onTCTCFailure(Call<ProductsSessionResult> call) {
                DialogUtils.dismissProgressDialog();
            }
        }, token, sessionId);
    }

    @Override
    public void getCartInfo(String token, int sessionId) {
        mInteractor.getCartInfo(new TCCCallback<CartInfoResult>() {
            @Override
            public void onTCTCSuccess(Call<CartInfoResult> call, Response<CartInfoResult> response) {
                if(response.body().getIsSuccess()) {
                    if(!response.body().getResults().getTotalCategory().equals("0")) {
                        mView.getCartInfoSuccess(response);
                    }else {
                        mView.getCartInfoFail();
                    }
                }
            }

            @Override
            public void onTCTCFailure(Call<CartInfoResult> call) {

            }
        }, token, sessionId);
    }

    @Override
    public void addItemToShoppingSession(String token, int sessionId, int productId, int quantity, String size) {
        DialogUtils.showProgressDialog(getViewContext());
        mInteractor.addItemToShoppingSession(new TCCCallback<RegisterResult>() {
            @Override
            public void onTCTCSuccess(Call<RegisterResult> call, Response<RegisterResult> response) {
                DialogUtils.dismissProgressDialog();
                if(response.body().getIsSuccess()) {
                    getCartInfo(token, sessionId);
//                    itemsInShoppingSession(token, sessionId);
                }
            }

            @Override
            public void onTCTCFailure(Call<RegisterResult> call) {
                DialogUtils.dismissProgressDialog();
            }
        }, token, sessionId, productId, quantity, size);
    }

    @Override
    public void deleteItemInShoppingSession(String token, int itemId, int sessionId) {
        DialogUtils.showProgressDialog(getViewContext());
        mInteractor.deleteItemInShoppingSession(new TCCCallback<RegisterResult>() {
            @Override
            public void onTCTCSuccess(Call<RegisterResult> call, Response<RegisterResult> response) {
                DialogUtils.dismissProgressDialog();
                if(response.body().getIsSuccess()) {
                    getCartInfo(token, sessionId);
                    itemsInShoppingSession(token, sessionId);
                }
            }

            @Override
            public void onTCTCFailure(Call<RegisterResult> call) {
                DialogUtils.dismissProgressDialog();
            }
        }, token, itemId, sessionId);
    }
}
