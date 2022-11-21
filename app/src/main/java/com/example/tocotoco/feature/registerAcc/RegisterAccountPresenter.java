package com.example.tocotoco.feature.registerAcc;

import android.widget.Toast;

import com.example.tocotoco.dialog.DialogUtils;
import com.example.tocotoco.feature.login.LoginContract;
import com.example.tocotoco.feature.login.LoginFragment;
import com.example.tocotoco.feature.login.LoginInteractor;
import com.example.tocotoco.model.RegisterResult;
import com.example.tocotoco.network.TCCCallback;
import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import retrofit2.Call;
import retrofit2.Response;

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

    @Override
    public void registerAcc(String name, String username, String pass, String mail, String phone) {
        DialogUtils.showProgressDialog(getViewContext());
        mInteractor.registerAcc(new TCCCallback<RegisterResult>() {
            @Override
            public void onViettelSuccess(Call<RegisterResult> call, Response<RegisterResult> response) {
                DialogUtils.dismissProgressDialog();
                if(response.body().getIsSuccess()){
                    mView.registerAccSuccess(response);
                }else {
                    Toast.makeText(getViewContext(), response.body().getErrorMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onViettelFailure(Call<RegisterResult> call) {
                DialogUtils.dismissProgressDialog();
            }
        }, name, username, pass, mail, phone);
    }
}
