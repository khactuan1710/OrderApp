package com.example.tocotoco.feature.order;

import com.example.tocotoco.dialog.DialogUtils;
import com.example.tocotoco.feature.product_detail.ProductDetailContract;
import com.example.tocotoco.feature.product_detail.ProductDetailFragment;
import com.example.tocotoco.feature.product_detail.ProductDetailInteractor;
import com.example.tocotoco.model.CartInfoResult;
import com.example.tocotoco.model.ProductsSessionResult;
import com.example.tocotoco.model.SessionIdResult;
import com.example.tocotoco.model.UserInfoResult;
import com.example.tocotoco.network.TCCCallback;
import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import retrofit2.Call;
import retrofit2.Response;

public class OrderPresenter extends Presenter<OrderContract.View, OrderContract.Interactor>
        implements OrderContract.Presenter{
    public OrderPresenter(ContainerView containerView) {
        super(containerView);
    }
    @Override
    public void start() {

    }

    @Override
    public OrderContract.Interactor onCreateInteractor() {
        return new OrderInteractor(this);
    }

    @Override
    public OrderContract.View onCreateView() {
        return OrderFragment.getInstance();
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
    public void getUserInfo(String token) {
        mInteractor.getUserInfo(new TCCCallback<UserInfoResult>() {
            @Override
            public void onTCTCSuccess(Call<UserInfoResult> call, Response<UserInfoResult> response) {
                if(response.body() != null) {
                    mView.getUserInfoSuccess(response);
                }
            }

            @Override
            public void onTCTCFailure(Call<UserInfoResult> call) {
                if(call != null) {

                }
            }
        }, token);
    }

    @Override
    public void getCartInfo(String token, int sessionId) {
        mInteractor.getCartInfo(new TCCCallback<CartInfoResult>() {
            @Override
            public void onTCTCSuccess(Call<CartInfoResult> call, Response<CartInfoResult> response) {
                mView.getCartInfoSuccess(response);
            }

            @Override
            public void onTCTCFailure(Call<CartInfoResult> call) {

            }
        }, token, sessionId);
    }
}
