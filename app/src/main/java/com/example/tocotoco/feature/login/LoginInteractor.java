package com.example.tocotoco.feature.login;

import com.example.tocotoco.model.DataTestResult;
import com.example.tocotoco.network.NetWorkController;
import com.example.tocotoco.network.TCCCallback;
import com.gemvietnam.base.viper.Interactor;

public class LoginInteractor extends Interactor<LoginContract.Presenter>
        implements LoginContract.Interactor{
    public LoginInteractor(LoginContract.Presenter presenter) {
        super(presenter);
    }

    @Override
    public void getListDataTest(String token, String contractId, String billCycle, TCCCallback<DataTestResult> callback) {
        NetWorkController.getListDataTest(callback,token, contractId, billCycle);
    }
}
