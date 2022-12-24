package com.example.tocotoco.feature.order;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.tocotoco.R;
import com.example.tocotoco.dialog.DialogUtils;
import com.example.tocotoco.feature.product_detail.ProductDetailContract;
import com.example.tocotoco.feature.product_detail.ProductDetailFragment;
import com.example.tocotoco.feature.product_detail.ProductDetailInteractor;
import com.example.tocotoco.model.CartInfoResult;
import com.example.tocotoco.model.ProductsSessionResult;
import com.example.tocotoco.model.RegisterResult;
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
    public void confirmOrder(String token, int sessionId, String provider, String phoneNumber, String address, String note) {
        DialogUtils.showProgressDialog(getViewContext());
        mInteractor.confirmOrder(new TCCCallback<RegisterResult>() {
            @Override
            public void onTCTCSuccess(Call<RegisterResult> call, Response<RegisterResult> response) {
                DialogUtils.dismissProgressDialog();
                if(response.body().getIsSuccess()) {
                    mView.confirmOrderSuccess(response);
                }else {
                    Toast.makeText(getViewContext(), response.body().getErrorMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onTCTCFailure(Call<RegisterResult> call) {
                DialogUtils.dismissProgressDialog();
            }
        }, token, sessionId, provider, phoneNumber, address, note);
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
