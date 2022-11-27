package com.example.tocotoco.feature.order;

import com.example.tocotoco.feature.product_detail.ProductDetailContract;
import com.example.tocotoco.feature.product_detail.ProductDetailFragment;
import com.example.tocotoco.feature.product_detail.ProductDetailInteractor;
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
    public void itemsInShoppingSession(String token, SessionIdResult.SessionId sessionId, boolean isUpdate) {

    }

    @Override
    public void getUserInfo(String token) {
        mInteractor.getUserInfo(new TCCCallback<UserInfoResult>() {
            @Override
            public void onTCTCSuccess(Call<UserInfoResult> call, Response<UserInfoResult> response) {
                if(response.body() != null) {

                }
            }

            @Override
            public void onTCTCFailure(Call<UserInfoResult> call) {
                if(call != null) {

                }
            }
        }, token);
    }
}
