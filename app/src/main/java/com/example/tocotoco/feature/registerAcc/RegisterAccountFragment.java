package com.example.tocotoco.feature.registerAcc;

import com.example.tocotoco.R;
import com.example.tocotoco.feature.login.LoginContract;
import com.example.tocotoco.feature.login.LoginFragment;
import com.gemvietnam.base.viper.ViewFragment;

public class RegisterAccountFragment extends ViewFragment<RegisterAccountContract.Presenter> implements RegisterAccountContract.View{
    public static RegisterAccountFragment getInstance() {
        return new RegisterAccountFragment();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.register_acc_fragment;
    }

}
