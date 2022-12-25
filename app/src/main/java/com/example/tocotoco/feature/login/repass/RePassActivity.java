package com.example.tocotoco.feature.login.repass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tocotoco.R;
import com.example.tocotoco.dialog.DialogUtils;
import com.example.tocotoco.model.LoginResult;
import com.example.tocotoco.network.NetWorkController;
import com.example.tocotoco.network.TCCCallback;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Response;

public class RePassActivity extends AppCompatActivity {

    TextView tv_re_pass;
    TextInputEditText ed_email;
    TextInputLayout lo_email;
    LinearLayout lnl_data, lnl_no_data;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_pass);
        tv_re_pass = findViewById(R.id.tv_login);
        ed_email = findViewById(R.id.ed_email);
        lo_email= findViewById(R.id.lo_sdt);
        lnl_data = findViewById(R.id.lnl_data);
        lnl_no_data = findViewById(R.id.lnl_no_data);
        context = this;
        tv_re_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ed_email.getText().toString().length() == 0) {
                    lo_email.setError("Email đang trống");
                    return;
                }
                lo_email.setError("");
                DialogUtils.showProgressDialog((Activity) context);
                NetWorkController.sendResetPasswordEmail(new TCCCallback<LoginResult>() {
                    @Override
                    public void onTCTCSuccess(Call<LoginResult> call, Response<LoginResult> response) {
                        if(response.body().getIsSuccess()) {
                            lnl_data.setVisibility(View.GONE);
                            lnl_no_data.setVisibility(View.VISIBLE);
                        }else {
                            Toast.makeText(context, "Email của bạn không đúng", Toast.LENGTH_LONG).show();
                        }
                        DialogUtils.dismissProgressDialog();
                    }

                    @Override
                    public void onTCTCFailure(Call<LoginResult> call) {

                    }
                }, ed_email.getText().toString());

            }
        });
    }
}