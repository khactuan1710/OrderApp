package com.example.tocotoco.feature.orderStatus;

import com.example.tocotoco.feature.base.TCCBaseActivity;
import com.example.tocotoco.feature.registerAcc.RegisterAccountPresenter;
import com.gemvietnam.base.viper.ViewFragment;

public class OrderStatusActivity extends TCCBaseActivity {
    @Override
    public ViewFragment onCreateFirstFragment() {
        return (ViewFragment) new OrderStatusPresenter(this).getFragment();
    }
}
