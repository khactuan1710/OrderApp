package com.example.tocotoco.feature.account;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.tocotoco.R;
import com.example.tocotoco.databinding.ActivityAccountBinding;
import com.example.tocotoco.dialog.DialogUtils;
import com.example.tocotoco.feature.account.network.ApiService;
import com.example.tocotoco.model.AccountResult;
import com.example.tocotoco.model.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity {
    private ActivityAccountBinding binding;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getUserInfor();
        binding.tvUpdateInfor.setOnClickListener(view -> {
            Intent intent = new Intent(AccountActivity.this, UpdateInforActivity.class);
            startActivity(intent);
        });
        binding.exit.setOnClickListener(view -> new AlertDialog.Builder(AccountActivity.this).setTitle("Xác nhận thoát")
                .setMessage("Bạn có muốn thoát không ?")
                .setNegativeButton("Hủy", (dialogInterface, i) -> dialogInterface.dismiss()).setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                }).create().show());
        binding.introduce.setOnClickListener(view -> startActivity(new Intent(AccountActivity.this, IntroduceActivity.class)));
    }

    private String getToken() {
        sharedPref = getSharedPreferences(this.getString(R.string.preference_file_key), MODE_PRIVATE);
        Log.e("Token", sharedPref.getString(this.getString(R.string.preference_key_token), ""));
        return sharedPref.getString(this.getString(R.string.preference_key_token), "");
    }

    private void getUserInfor() {
        DialogUtils.showProgressDialog(this);
        ApiService.API_SERVICE.getUserInfor(getToken()).enqueue(new Callback<AccountResult>() {
            @Override
            public void onResponse(Call<AccountResult> call, Response<AccountResult> response) {
                if (response.body() != null) {
                    AccountResult accountResult = response.body();
                    Result result = accountResult.getResult();
                    binding.name.setText(result.getName());
                    DialogUtils.dismissProgressDialog();
                }
            }

            @Override
            public void onFailure(Call<AccountResult> call, Throwable t) {

            }
        });
    }

}