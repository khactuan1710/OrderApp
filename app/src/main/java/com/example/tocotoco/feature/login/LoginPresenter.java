package com.example.tocotoco.feature.login;

import android.util.Log;

import com.example.tocotoco.dialog.DialogUtils;
import com.example.tocotoco.model.CategoriesResult;
import com.example.tocotoco.model.DataTestResult;
import com.example.tocotoco.network.TCCCallback;
import com.example.tocotoco.util.NetworkUtils;
import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class LoginPresenter extends Presenter<LoginContract.View, LoginContract.Interactor>
        implements LoginContract.Presenter {

    public LoginPresenter(ContainerView containerView) {
        super(containerView);
    }
    @Override
    public void start() {

    }

    @Override
    public LoginContract.Interactor onCreateInteractor() {
        return new LoginInteractor(this);
    }

    @Override
    public LoginContract.View onCreateView() {
        return LoginFragment.getInstance();
    }

    @Override
    public void getListDataTest() {
        DialogUtils.showProgressDialog(getViewContext());
//        if (NetworkUtils.isConnect(getViewContext())) {
//            mInteractor.getListDataTest("UserModel.loadData(mView.getViewContext()).getToken()", "fd", "fdsd",
//                    new TCCCallback<DataTestResult>() {
//                        @Override
//                        public void onViettelSuccess(Call<DataTestResult> call, Response<DataTestResult> response) {
//                            if(response != null) {
//                                mView.initListDataTest(response);
//                            }
//                            DialogUtils.dismissProgressDialog();
//                        }
//
//                        @Override
//                        public void onViettelFailure(Call<DataTestResult> call) {
//
//                        }
//                    }
//            );
//        }
//        if (NetworkUtils.isConnect(getViewContext())) {
//            mInteractor.getListCategories(
//                    new TCCCallback<CategoriesResult>() {
//                        @Override
//                        public void onViettelSuccess(Call<CategoriesResult> call, Response<CategoriesResult> response) {
//                            if(response != null) {
////                                mView.initListDataTest(response);
//                            }
//                            DialogUtils.dismissProgressDialog();
//                        }
//
//                        @Override
//                        public void onViettelFailure(Call<CategoriesResult> call) {
//                            if(call != null) {
//
//                            }
//                        }
//
//                        @Override
//                        protected void onViettelFailure(Call<CategoriesResult> call, Response<CategoriesResult> response) {
//                            super.onViettelFailure(call, response);
//                        }
//
//                        @Override
//                        public void onFailure(Call<CategoriesResult> call, Throwable t) {
//                            super.onFailure(call, t);
//                        }
//
//                        @Override
//                        public void onResponse(Call<CategoriesResult> call, Response<CategoriesResult> response) {
//                            super.onResponse(call, response);
//                        }
//                    }
//            );
//        }
        if (NetworkUtils.isConnect(getViewContext())) {
            mInteractor.getListCategories(
                    new TCCCallback<CategoriesResult>() {
                        @Override
                        public void onViettelSuccess(Call<CategoriesResult> call, Response<CategoriesResult> response) {
                            if(response != null) {
//                                mView.initListDataTest(response);
                                response.body().getResult();
                            }
                            DialogUtils.dismissProgressDialog();
                        }

                        @Override
                        public void onViettelFailure(Call<CategoriesResult> call) {
                            if(call != null) {

                            }
                        }

                        @Override
                        protected void onViettelFailure(Call<CategoriesResult> call, Response<CategoriesResult> response) {
                            super.onViettelFailure(call, response);
                        }

                        @Override
                        public void onFailure(Call<CategoriesResult> call, Throwable t) {
                            super.onFailure(call, t);
                        }

                        @Override
                        public void onResponse(Call<CategoriesResult> call, Response<CategoriesResult> response) {
                            super.onResponse(call, response);
                        }
                    }, 1
            );
        }
    }
}
