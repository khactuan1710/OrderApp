package com.example.tocotoco.feature.registerAcc;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tocotoco.R;
import com.example.tocotoco.feature.login.LoginActivity;
import com.example.tocotoco.feature.login.LoginContract;
import com.example.tocotoco.feature.login.LoginFragment;
import com.example.tocotoco.model.ProductsResult;
import com.example.tocotoco.model.RegisterResult;
import com.example.tocotoco.network.NetWorkController;
import com.example.tocotoco.network.TCCCallback;
import com.gemvietnam.base.viper.ViewFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Response;

public class RegisterAccountFragment extends ViewFragment<RegisterAccountContract.Presenter> implements RegisterAccountContract.View, View.OnClickListener{

    @BindView(R.id.btn_register)
    TextView btn_register;
    @BindView(R.id.ed_name)
    TextInputEditText ed_name;
    @BindView(R.id.ed_phone_number)
    TextInputEditText ed_phone_number;
    @BindView(R.id.ed_username)
    TextInputEditText ed_username;
    @BindView(R.id.ed_pass)
    TextInputEditText ed_pass;
    @BindView(R.id.ed_repass)
    TextInputEditText ed_repass;
    @BindView(R.id.lo_username)
    TextInputLayout lo_username;
    @BindView(R.id.lo_phone_number)
    TextInputLayout lo_phone_number;
    @BindView(R.id.lo_name)
    TextInputLayout lo_name;
    @BindView(R.id.lo_pass)
    TextInputLayout lo_pass;
    @BindView(R.id.lo_repass)
    TextInputLayout lo_repass;

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
        setListener();
    }

    private void setListener() {
        btn_register.setOnClickListener(this);
        ed_name.setOnClickListener(this);
        ed_phone_number.setOnClickListener(this);
        ed_username.setOnClickListener(this);
        ed_pass.setOnClickListener(this);
        ed_repass.setOnClickListener(this);
    }

    @Override
    public void registerAccSuccess(Response<RegisterResult> data) {
        Toast.makeText(getViewContext(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
        Intent i = new Intent(getViewContext(), LoginActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                String name = ed_name.getText().toString();
                String phoneNumber = ed_phone_number.getText().toString();
                String username = ed_username.getText().toString();
                String pass = ed_pass.getText().toString();
                String rePass = ed_repass.getText().toString();
                if(name.equals("")) {
                    lo_name.setError("Tên không được trống!");
                    return;
                }
                if(phoneNumber.equals("")) {
                    lo_phone_number.setError("Số điện thoại không được trống!");
                    return;
                }
                if(username.equals("")) {
                    lo_username.setError("Tên đăng nhập không được trống!");
                    return;
                }
                if(pass.equals("")) {
                    lo_pass.setError("Mật khẩu không được trống!");
                    return;
                }
                if(rePass.equals("")) {
                    lo_repass.setError("Mật khẩu xác nhận không được trống!");
                    return;
                }
                if(!pass.equals(rePass)) {
                    lo_repass.setError("Mật khẩu xác nhận không đúng!");
                    return;
                }
                mPresenter.registerAcc(name, username, pass, "", phoneNumber);
                break;

        }
    }
}
