package com.example.tocotoco.feature.order;

import android.content.Intent;

import com.example.tocotoco.feature.base.TCCBaseActivity;
import com.example.tocotoco.feature.product_detail.ProductDetailPresenter;
import com.gemvietnam.base.viper.ViewFragment;

import vn.zalopay.sdk.ZaloPaySDK;

public class OrderActivity extends TCCBaseActivity {
    @Override
    public ViewFragment onCreateFirstFragment() {
        return (ViewFragment) new OrderPresenter(this).getFragment();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }
}
