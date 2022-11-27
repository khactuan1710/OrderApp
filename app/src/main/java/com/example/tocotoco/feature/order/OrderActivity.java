package com.example.tocotoco.feature.order;

import com.example.tocotoco.feature.base.TCCBaseActivity;
import com.example.tocotoco.feature.product_detail.ProductDetailPresenter;
import com.gemvietnam.base.viper.ViewFragment;

public class OrderActivity extends TCCBaseActivity {
    @Override
    public ViewFragment onCreateFirstFragment() {
        return (ViewFragment) new OrderPresenter(this).getFragment();
    }
}
