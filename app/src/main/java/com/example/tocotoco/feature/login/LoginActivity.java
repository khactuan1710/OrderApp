package com.example.tocotoco.feature.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.example.tocotoco.R;
import com.example.tocotoco.feature.base.TCCBaseActivity;
import com.gemvietnam.base.viper.ViewFragment;

public class LoginActivity extends TCCBaseActivity{

    @Override
    public ViewFragment onCreateFirstFragment() {
        return (ViewFragment) new LoginPresenter(this).getFragment();
    }
}