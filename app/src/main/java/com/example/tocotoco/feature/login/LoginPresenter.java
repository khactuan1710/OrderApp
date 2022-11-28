package com.example.tocotoco.feature.login;

import android.widget.Toast;

import com.example.tocotoco.dialog.DialogUtils;
import com.example.tocotoco.model.LoginResult;
import com.example.tocotoco.network.TCCCallback;
import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

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
    public void loginWithPass(String username, String pass, String type, String token_device) {
        DialogUtils.showProgressDialog(getViewContext());
        mInteractor.loginWithPass(new TCCCallback<LoginResult>() {
            @Override
            public void onTCTCSuccess(Call<LoginResult> call, Response<LoginResult> response) {
                DialogUtils.dismissProgressDialog();
                if(response.body().getIsSuccess()) {
                    mView.loginSuccess(response);
                }else {
                    Toast.makeText(getViewContext(), response.body().getErrorMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onTCTCFailure(Call<LoginResult> call) {
                DialogUtils.dismissProgressDialog();
            }
        }, username, pass, type, token_device);
    }
}
