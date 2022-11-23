package com.example.tocotoco.feature.product_detail;

import com.example.tocotoco.dialog.DialogUtils;
import com.example.tocotoco.model.ProductResult;
import com.example.tocotoco.model.RegisterResult;
import com.example.tocotoco.network.TCCCallback;
import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import retrofit2.Call;
import retrofit2.Response;

public class ProductDetailPresenter extends Presenter<ProductDetailContract.View, ProductDetailContract.Interactor>
        implements ProductDetailContract.Presenter{

    public ProductDetailPresenter(ContainerView containerView) {
        super(containerView);
    }
    @Override
    public void start() {

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
            public void onViettelSuccess(Call<ProductResult> call, Response<ProductResult> response) {
                DialogUtils.dismissProgressDialog();
                mView.initViewDetail(response);
            }

            @Override
            public void onViettelFailure(Call<ProductResult> call) {
                DialogUtils.dismissProgressDialog();
            }
        }, id);
    }

    @Override
    public void getUserShoppingSession(String token) {

    }

    @Override
    public void addItemToShoppingSession(String token, int sessionId, int productId, int quantity, String size) {

    }

    @Override
    public void addFavItem(String token, int productId) {
        DialogUtils.showProgressDialog(getViewContext());
        mInteractor.addFavItem(new TCCCallback<RegisterResult>() {
            @Override
            public void onViettelSuccess(Call<RegisterResult> call, Response<RegisterResult> response) {
                DialogUtils.dismissProgressDialog();
            }

            @Override
            public void onViettelFailure(Call<RegisterResult> call) {
                DialogUtils.dismissProgressDialog();
            }
        }, token, productId);
    }

    @Override
    public void deleteFavItem(String token, int productId) {
        DialogUtils.showProgressDialog(getViewContext());
        mInteractor.deleteFavItem(new TCCCallback<RegisterResult>() {
            @Override
            public void onViettelSuccess(Call<RegisterResult> call, Response<RegisterResult> response) {
                DialogUtils.dismissProgressDialog();
            }

            @Override
            public void onViettelFailure(Call<RegisterResult> call) {
                DialogUtils.dismissProgressDialog();
            }
        }, token, productId);
    }
}
