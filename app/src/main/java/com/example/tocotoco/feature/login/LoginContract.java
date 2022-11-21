package com.example.tocotoco.feature.login;

import com.example.tocotoco.model.CategoriesResult;
import com.example.tocotoco.model.DataTestResult;
import com.example.tocotoco.model.LoginResult;
import com.example.tocotoco.network.TCCCallback;
import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import retrofit2.Response;

public interface LoginContract {
    interface Interactor extends IInteractor<Presenter> {
        void loginWithPass(TCCCallback<LoginResult> callback, String username, String pass, String type);
    }

    interface View extends PresentView<Presenter> {
        void loginSuccess(Response<LoginResult> data);
    }

    interface Presenter extends IPresenter<View, Interactor> {
        void loginWithPass(String username, String pass, String type);
    }
}
