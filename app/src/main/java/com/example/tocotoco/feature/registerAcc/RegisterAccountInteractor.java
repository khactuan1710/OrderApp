package com.example.tocotoco.feature.registerAcc;

import com.example.tocotoco.feature.login.LoginContract;
import com.gemvietnam.base.viper.Interactor;

public class RegisterAccountInteractor extends Interactor<RegisterAccountContract.Presenter>
        implements RegisterAccountContract.Interactor{
    public RegisterAccountInteractor(RegisterAccountContract.Presenter presenter) {
        super(presenter);
    }
}
