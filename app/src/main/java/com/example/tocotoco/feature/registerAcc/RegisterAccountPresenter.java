package com.example.tocotoco.feature.registerAcc;

import com.example.tocotoco.feature.login.LoginContract;
import com.example.tocotoco.feature.login.LoginFragment;
import com.example.tocotoco.feature.login.LoginInteractor;
import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

public class RegisterAccountPresenter extends Presenter<RegisterAccountContract.View, RegisterAccountContract.Interactor>
        implements RegisterAccountContract.Presenter{
    public RegisterAccountPresenter(ContainerView containerView) {
        super(containerView);
    }
    @Override
    public void start() {

    }

    @Override
    public RegisterAccountContract.Interactor onCreateInteractor() {
        return new RegisterAccountInteractor(this);
    }

    @Override
    public RegisterAccountContract.View onCreateView() {
        return RegisterAccountFragment.getInstance();
    }
}
