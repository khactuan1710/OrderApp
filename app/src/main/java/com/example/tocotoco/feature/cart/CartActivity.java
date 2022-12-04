package com.example.tocotoco.feature.cart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tocotoco.R;
import com.example.tocotoco.feature.base.TCCBaseActivity;
import com.example.tocotoco.feature.order.OrderPresenter;
import com.gemvietnam.base.viper.ViewFragment;

public class CartActivity extends TCCBaseActivity {

    @Override
    public ViewFragment onCreateFirstFragment() {
        return (ViewFragment) new CartPresenter(this).getFragment();
    }
}