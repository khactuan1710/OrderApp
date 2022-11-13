package com.example.tocotoco.feature.registerAcc;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.tocotoco.R;
import com.example.tocotoco.feature.login.LoginContract;
import com.example.tocotoco.feature.login.LoginFragment;
import com.example.tocotoco.model.ProductsResult;
import com.example.tocotoco.network.NetWorkController;
import com.example.tocotoco.network.TCCCallback;
import com.gemvietnam.base.viper.ViewFragment;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Response;

public class RegisterAccountFragment extends ViewFragment<RegisterAccountContract.Presenter> implements RegisterAccountContract.View{

    @BindView(R.id.btn_register)
    TextView btn_register;

    public static RegisterAccountFragment getInstance() {
        return new RegisterAccountFragment();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.register_acc_fragment;
    }

    @Override
    public void initLayout() {
        super.initLayout();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("log", "click");
                NetWorkController.getListProduct(new TCCCallback<ProductsResult>() {
                    @Override
                    public void onViettelSuccess(Call<ProductsResult> call, Response<ProductsResult> response) {
                        if(response != null) {

                        }
                    }

                    @Override
                    public void onViettelFailure(Call<ProductsResult> call) {

                    }
                });
            }
        });
    }
}
