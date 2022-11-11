package com.example.tocotoco.feature.login;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.tocotoco.R;
import com.example.tocotoco.model.CategoriesResult;
import com.example.tocotoco.model.DataTestResult;
import com.example.tocotoco.network.NetWorkController;
import com.example.tocotoco.network.TCCCallback;
import com.gemvietnam.base.viper.ViewFragment;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Response;

public class LoginFragment extends ViewFragment<LoginContract.Presenter> implements LoginContract.View {

    @BindView(R.id.tv)
    TextView tv;

    public static LoginFragment getInstance() {
        return new LoginFragment();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.login_fragment;
    }

    @Override
    public void initLayout() {
        super.initLayout();
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mPresenter.getListDataTest();
                NetWorkController.getListCategories(new TCCCallback<CategoriesResult>() {
                    @Override
                    public void onViettelSuccess(Call<CategoriesResult> call, Response<CategoriesResult> response) {
                        if(response != null) {
                            Log.e("res", response.body().getMessage());
                        }
                    }

                    @Override
                    public void onViettelFailure(Call<CategoriesResult> call) {

                    }
                }, 1);
            }
        });
    }

    @Override
    public void initListDataTest(Response<DataTestResult> data) {
        if(data.body() != null) {
            tv.setText(data.body().getMessage());
        }
    }
}
