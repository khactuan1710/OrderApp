package com.example.tocotoco.feature.registerAcc;

import com.example.tocotoco.feature.login.LoginContract;
import com.example.tocotoco.model.CategoriesResult;
import com.example.tocotoco.model.DataTestResult;
import com.example.tocotoco.model.LoginResult;
import com.example.tocotoco.model.RegisterResult;
import com.example.tocotoco.network.TCCCallback;
import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import retrofit2.Response;

public interface RegisterAccountContract {
    interface Interactor extends IInteractor<RegisterAccountContract.Presenter> {
        void registerAcc(TCCCallback<RegisterResult> callback, String name, String username, String pass, String mail, String phone);
    }

    interface View extends PresentView<RegisterAccountContract.Presenter> {
        void registerAccSuccess(Response<RegisterResult> data);
    }

    interface Presenter extends IPresenter<RegisterAccountContract.View, RegisterAccountContract.Interactor> {
        void registerAcc(String name, String username, String pass, String mail, String phone);
    }
}
