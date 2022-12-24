package com.example.tocotoco.feature.product_detail;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;

import com.example.tocotoco.R;
import com.example.tocotoco.dialog.DialogUtils;
import com.example.tocotoco.model.ProductResult;
import com.example.tocotoco.model.ProductsSessionResult;
import com.example.tocotoco.model.RegisterResult;
import com.example.tocotoco.model.SessionIdResult;
import com.example.tocotoco.network.NetWorkController;
import com.example.tocotoco.network.TCCCallback;
import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import retrofit2.Call;
import retrofit2.Response;

public class ProductDetailPresenter extends Presenter<ProductDetailContract.View, ProductDetailContract.Interactor>
        implements ProductDetailContract.Presenter{

    SessionIdResult.SessionId sessionIdJS;
    SharedPreferences.Editor editor;
    public ProductDetailPresenter(ContainerView containerView) {
        super(containerView);
    }
    @Override
    public void start() {
        SharedPreferences sharedPref = getViewContext().getSharedPreferences(
                getViewContext().getString(R.string.preference_file_key), MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    @Override
    public ProductDetailContract.Interactor onCreateInteractor() {
        return new ProductDetailInteractor(this);
    }

    @Override
    public ProductDetailContract.View onCreateView() {
        return ProductDetailFragment.getInstance();
    }

    @Override
    public void getProductDetail(int id) {
        DialogUtils.showProgressDialog(getViewContext());
        mInteractor.getProductDetail(new TCCCallback<ProductResult>() {
            @Override
            public void onTCTCSuccess(Call<ProductResult> call, Response<ProductResult> response) {
                DialogUtils.dismissProgressDialog();
                mView.initViewDetail(response);
            }

            @Override
            public void onTCTCFailure(Call<ProductResult> call) {
                DialogUtils.dismissProgressDialog();
            }
        }, id);
    }

    @Override
    public void getUserShoppingSession(String token) {
        DialogUtils.showProgressDialog(getViewContext());
        mInteractor.getUserShoppingSession(new TCCCallback<SessionIdResult>() {
            @Override
            public void onTCTCSuccess(Call<SessionIdResult> call, Response<SessionIdResult> response) {
                DialogUtils.dismissProgressDialog();
                if(response.body().getIsSuccess()) {
                    sessionIdJS = response.body().getResult();
                    editor.putInt(getViewContext().getString(R.string.session_id), response.body().getResult().getId());
                    editor.apply();
                    itemsInShoppingSession(token, response.body().getResult(), false);
                    mView.receiveSession(response.body().getResult());
                }else {
                    if(!token.equals("")) {
                        createShoppingSession(token);
                    }
                }
            }

            @Override
            public void onTCTCFailure(Call<SessionIdResult> call) {
                DialogUtils.dismissProgressDialog();
            }
        }, token);
    }

    @Override
    public void checkFav(String token, int productId) {
        NetWorkController.checkFav(new TCCCallback<RegisterResult>() {
            @Override
            public void onTCTCSuccess(Call<RegisterResult> call, Response<RegisterResult> response) {
                mView.checkFav(response.body().isResult());
            }

            @Override
            public void onTCTCFailure(Call<RegisterResult> call) {

            }
        }, token, productId);
    }

    @Override
    public void createShoppingSession(String token) {
        mInteractor.createShoppingSession(new TCCCallback<RegisterResult>() {
            @Override
            public void onTCTCSuccess(Call<RegisterResult> call, Response<RegisterResult> response) {
                getUserShoppingSession(token);
            }

            @Override
            public void onTCTCFailure(Call<RegisterResult> call) {

            }
        }, token);
    }

    @Override
    public void itemsInShoppingSession(String token, SessionIdResult.SessionId sessionId, boolean isUpdate) {
        mInteractor.itemsInShoppingSession(new TCCCallback<ProductsSessionResult>() {
            @Override
            public void onTCTCSuccess(Call<ProductsSessionResult> call, Response<ProductsSessionResult> response) {
                mView.updateQuantityCart(response, isUpdate);
            }

            @Override
            public void onTCTCFailure(Call<ProductsSessionResult> call) {
            }
        }, token, sessionId.getId());
    }

    @Override
    public void addItemToShoppingSession(String token, int sessionId, int productId, int quantity, String size) {
        DialogUtils.showProgressDialog(getViewContext());
        mInteractor.addItemToShoppingSession(new TCCCallback<RegisterResult>() {
            @Override
            public void onTCTCSuccess(Call<RegisterResult> call, Response<RegisterResult> response) {
                DialogUtils.dismissProgressDialog();
                itemsInShoppingSession(token, sessionIdJS, true);
                mView.addItemToCartSuccess(response.body().getIsSuccess());
            }

            @Override
            public void onTCTCFailure(Call<RegisterResult> call) {
                DialogUtils.dismissProgressDialog();
            }
        }, token, sessionId, productId, quantity, size);
    }

    @Override
    public void addFavItem(String token, int productId) {
        mInteractor.addFavItem(new TCCCallback<RegisterResult>() {
            @Override
            public void onTCTCSuccess(Call<RegisterResult> call, Response<RegisterResult> response) {
            }

            @Override
            public void onTCTCFailure(Call<RegisterResult> call) {
            }
        }, token, productId);
    }

    @Override
    public void deleteFavItem(String token, int productId) {
        mInteractor.deleteFavItem(new TCCCallback<RegisterResult>() {
            @Override
            public void onTCTCSuccess(Call<RegisterResult> call, Response<RegisterResult> response) {
            }

            @Override
            public void onTCTCFailure(Call<RegisterResult> call) {
            }
        }, token, productId);
    }

    @Override
    public void deleteItemInShoppingSession(String token, int itemId, int sessionId) {
        DialogUtils.showProgressDialog(getViewContext());
        mInteractor.deleteItemInShoppingSession(new TCCCallback<RegisterResult>() {
            @Override
            public void onTCTCSuccess(Call<RegisterResult> call, Response<RegisterResult> response) {
                DialogUtils.dismissProgressDialog();
                mView.addItemToCartSuccess(response.body().getIsSuccess());
            }

            @Override
            public void onTCTCFailure(Call<RegisterResult> call) {
                DialogUtils.dismissProgressDialog();
            }
        }, token, itemId, sessionId);
    }
}
