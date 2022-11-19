package com.example.tocotoco.feature.registerAcc;

import com.example.tocotoco.feature.login.LoginContract;
import com.example.tocotoco.model.RegisterResult;
import com.example.tocotoco.network.NetWorkController;
import com.example.tocotoco.network.TCCCallback;
import com.gemvietnam.base.viper.Interactor;

public class RegisterAccountInteractor extends Interactor<RegisterAccountContract.Presenter>
        implements RegisterAccountContract.Interactor{
    public RegisterAccountInteractor(RegisterAccountContract.Presenter presenter) {
        super(presenter);
    }

    @Override
    public void registerAcc(TCCCallback<RegisterResult> callback, String name, String username, String password, String email, String phone) {
        NetWorkController.registerAcc(callback, name, username, password, email, phone);
    }
}
