package com.example.tocotoco.feature.registerAcc;

import com.example.tocotoco.feature.base.TCCBaseActivity;
import com.example.tocotoco.feature.login.LoginPresenter;
import com.gemvietnam.base.viper.ViewFragment;

public class RegisterAccountActivity extends TCCBaseActivity {
    @Override
    public ViewFragment onCreateFirstFragment() {
        return (ViewFragment) new RegisterAccountPresenter(this).getFragment();
    }
}
