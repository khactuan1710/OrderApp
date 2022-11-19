package com.example.tocotoco.feature.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tocotoco.R;
import com.example.tocotoco.model.LoginResult;
import com.gemvietnam.base.viper.ViewFragment;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import retrofit2.Response;

public class LoginFragment extends ViewFragment<LoginContract.Presenter> implements LoginContract.View, View.OnClickListener {

    @BindView(R.id.tv_login)
    TextView tv_login;
    @BindView(R.id.ed_sdt)
    TextInputEditText ed_sdt;
    @BindView(R.id.etPassword)
    TextInputEditText etPassword;
    Context context;

    SharedPreferences sharedPref = context.getSharedPreferences(
            context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPref.edit();

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
        setListener();
    }

    private void setListener() {
        tv_login.setOnClickListener(this);
        ed_sdt.setOnClickListener(this);
        etPassword.setOnClickListener(this);
    }


    @Override
    public void loginSuccess(Response<LoginResult> data) {
        if (data.body() != null) {
            editor.putString(context.getString(R.string.preference_key_token), data.body().getResult());
            editor.apply();
        }
        Toast.makeText(getViewContext(), "Tài khoản chính xác", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                mPresenter.loginWithPass(ed_sdt.getText().toString(), etPassword.getText().toString(), "username");
                break;
            case R.id.ed_sdt:
                Log.e("tag:  ", "cldsvick");
                break;

            case R.id.etPassword:
                Log.e("tag:  ", "cdslick");
                break;
        }
    }
}
