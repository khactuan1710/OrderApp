package com.example.tocotoco.feature.account.activity;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tocotoco.R;
import com.example.tocotoco.databinding.FragmentUpdateInforBinding;
import com.example.tocotoco.dialog.DialogUtils;
import com.example.tocotoco.feature.account.network.ApiService;
import com.example.tocotoco.model.AccountResult;
import com.example.tocotoco.model.Result;
import com.example.tocotoco.model.UpdateAccountResult;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateInforFragment extends Fragment {
private FragmentUpdateInforBinding binding;
    SharedPreferences sharedPref;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentUpdateInforBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        backActivity();
        getUserInfor();
        updateInfor();
    }

    private void backActivity(){
        binding.backActivity.setOnClickListener(view -> requireActivity().getSupportFragmentManager().popBackStack());
    }

    private boolean validate() {
        if (binding.username.getText().toString().isEmpty()) {
            Toast.makeText(requireContext(), "Không để trống username", Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.name.getText().toString().isEmpty()) {
            Toast.makeText(requireContext(), "Không để trống tên", Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.email.getText().toString().isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(binding.email.getText().toString()).matches()) {
            Toast.makeText(requireContext(), "Không để trống và email phải hợp lệ", Toast.LENGTH_SHORT).show();
            return false;
        }else if (binding.sdt.getText().toString().isEmpty()){
            Toast.makeText(requireContext(), "Không để trống số điện thoại", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void updateInfor() {
        binding.update.setOnClickListener(view -> {
            if (validate()){
                DialogUtils.showProgressDialog(requireActivity());
                ApiService.API_SERVICE.updateUserInfor(getToken(), binding.username.getText().toString(), binding.name.getText().toString(),
                        binding.email.getText().toString(), binding.sdt.getText().toString()).enqueue(new Callback<UpdateAccountResult>() {
                    @Override
                    public void onResponse(Call<UpdateAccountResult> call, Response<UpdateAccountResult> response) {
                        DialogUtils.dismissProgressDialog();
                        Toast.makeText(requireContext(), "Lưu thông tin thành công", Toast.LENGTH_SHORT).show();
                        AccountFragment fragment=new AccountFragment();
                        Bundle bundle=new Bundle();
                        bundle.putString("name",binding.name.getText().toString());
                        fragment.setArguments(bundle);
                    }

                    @Override
                    public void onFailure(Call<UpdateAccountResult> call, Throwable t) {
                        DialogUtils.dismissProgressDialog();
                        Toast.makeText(requireContext(), "Lưu thông tin thất bại:"+t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("Loi Update",t.getMessage());
                    }
                });
            }
        });

    }

    private String getToken() {
        sharedPref = requireActivity().getSharedPreferences(this.getString(R.string.preference_file_key), MODE_PRIVATE);
        Log.e("Token", sharedPref.getString(this.getString(R.string.preference_key_token), ""));
        return sharedPref.getString(this.getString(R.string.preference_key_token), "");
    }

    private void getUserInfor() {
        DialogUtils.showProgressDialog(requireActivity());
        ApiService.API_SERVICE.getUserInfor(getToken()).enqueue(new Callback<AccountResult>() {
            @Override
            public void onResponse(Call<AccountResult> call, Response<AccountResult> response) {
                if (response.body() != null) {
                    AccountResult accountResult = response.body();
                    Result result = accountResult.getResult();
                    binding.name.setText(result.getName());
                    binding.linearLayout2.setBackgroundResource(R.drawable.bg_img_account);
                    binding.linearLayout2.setText(result.getName().substring(0,1).toUpperCase(Locale.getDefault()));
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