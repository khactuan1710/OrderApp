package com.example.tocotoco.feature.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tocotoco.R;
import com.example.tocotoco.databinding.ActivityUpdateInforBinding;
import com.example.tocotoco.dialog.DialogUtils;
import com.example.tocotoco.feature.account.network.ApiService;
import com.example.tocotoco.model.AccountResult;
import com.example.tocotoco.model.Result;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateInforActivity extends AppCompatActivity {
    private ActivityUpdateInforBinding binding;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateInforBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.backActivity.setOnClickListener(view -> onBackPressed());
        getUserInfor();
        binding.update.setOnClickListener(view -> {
            if (validate()){
                updateInfor();
            }

        });

    }

    private boolean validate() {
        if (binding.username.getText().toString().isEmpty()) {
            Toast.makeText(this, "Không để trống username", Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.name.getText().toString().isEmpty()) {
            Toast.makeText(this, "Không để trống tên", Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.email.getText().toString().isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(binding.email.getText().toString()).matches()) {
            Toast.makeText(this, "Không để trống và email phải hợp lệ", Toast.LENGTH_SHORT).show();
            return false;
        }else if (binding.sdt.getText().toString().isEmpty()){
            Toast.makeText(this, "Không để trống số điện thoại", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void updateInfor() {
        DialogUtils.showProgressDialog(this);
        ApiService.API_SERVICE.updateUserInfor(getToken(), binding.username.getText().toString(), binding.name.getText().toString(),
                binding.email.getText().toString(), binding.sdt.getText().toString()).enqueue(new Callback<AccountResult>() {
            @Override
            public void onResponse(Call<AccountResult> call, Response<AccountResult> response) {
                DialogUtils.dismissProgressDialog();
                Toast.makeText(UpdateInforActivity.this, "Lưu thông tin thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<AccountResult> call, Throwable t) {
                DialogUtils.dismissProgressDialog();
                Toast.makeText(UpdateInforActivity.this, "Lưu thông tin thất bại:"+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Loi Update",t.getMessage());
            }
        });
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
                    binding.username.setText(result.getUsername());
                    binding.email.setText(result.getEmail());
                    binding.sdt.setText(result.getPhonenumber());
                    DialogUtils.dismissProgressDialog();
                }
            }

            @Override
            public void onFailure(Call<AccountResult> call, Throwable t) {

            }
        });
    }

}