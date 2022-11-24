package com.example.tocotoco.feature.product_detail;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.tocotoco.R;
import com.example.tocotoco.feature.base.TCCBaseActivity;
import com.example.tocotoco.feature.login.LoginPresenter;
import com.gemvietnam.base.viper.ViewFragment;

public class ProductDetailActivity extends TCCBaseActivity {

    @Override
    public ViewFragment onCreateFirstFragment() {
        return (ViewFragment) new ProductDetailPresenter(this).getFragment();
    }
}