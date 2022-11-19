package com.example.tocotoco.feature.login;

import com.example.tocotoco.model.CategoriesResult;
import com.example.tocotoco.model.DataTestResult;
import com.example.tocotoco.model.LoginResult;
import com.example.tocotoco.network.NetWorkController;
import com.example.tocotoco.network.TCCCallback;
import com.gemvietnam.base.viper.Interactor;

public class LoginInteractor extends Interactor<LoginContract.Presenter>
        implements LoginContract.Interactor{
    public LoginInteractor(LoginContract.Presenter presenter) {
        super(presenter);
    }


    @Override
    public void loginWithPass(TCCCallback<LoginResult> callback, String username, String pass, String type) {
        NetWorkController.loginWithPass(callback, username, pass, type);
    }

}
