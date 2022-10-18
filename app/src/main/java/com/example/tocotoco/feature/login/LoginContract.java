package com.example.tocotoco.feature.login;

import com.example.tocotoco.model.DataTestResult;
import com.example.tocotoco.network.TCCCallback;
import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import retrofit2.Response;

public interface LoginContract {
    interface Interactor extends IInteractor<Presenter> {
        void getListDataTest(String token, String contractId, String billCycle, TCCCallback<DataTestResult> callback);
    }

    interface View extends PresentView<Presenter> {
        void initListDataTest(Response<DataTestResult> data);
    }

    interface Presenter extends IPresenter<View, Interactor> {
        void getListDataTest();
    }
}
