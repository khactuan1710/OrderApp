package com.example.tocotoco.feature.account.activity;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tocotoco.R;
import com.example.tocotoco.databinding.FragmentAccountBinding;
import com.example.tocotoco.dialog.DialogUtils;
import com.example.tocotoco.feature.account.fragment.IntroductFragment;
import com.example.tocotoco.feature.account.fragment.ListStoreFragment;
import com.example.tocotoco.feature.account.fragment.RulesFragment;
import com.example.tocotoco.feature.account.fragment.SupportFragment;
import com.example.tocotoco.feature.account.fragment.VersionFragment;
import com.example.tocotoco.feature.account.network.ApiService;
import com.example.tocotoco.feature.login.LoginActivity;
import com.example.tocotoco.model.AccountResult;
import com.example.tocotoco.model.Result;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment {
    private FragmentAccountBinding binding;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkUserNull();
        setUpdata();
        login();
        logoutUser();
        exitApp();
        aboutToCoToCo();
        aboutApp();
        rulesApp();
        listStore();
        support();
    }

    private void support() {
        binding.layoutSupport.setOnClickListener(view -> requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_frame, new SupportFragment())
                .addToBackStack(null).commit());
    }

    private void listStore() {
        binding.listStore.setOnClickListener(view -> requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_frame, new ListStoreFragment())
                .addToBackStack(null).commit());
    }

    private void rulesApp() {
        binding.rules.setOnClickListener(view -> requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_frame, new RulesFragment())
                .addToBackStack(null).commit());
    }

    private void aboutApp() {
        binding.aboutApp.setOnClickListener(view -> requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_frame, new VersionFragment())
                .addToBackStack(null).commit());
    }

    private void aboutToCoToCo() {
        binding.introduce.setOnClickListener(view -> requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_frame, new IntroductFragment())
                .addToBackStack(null).commit());
    }

    private void setUpdata() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            binding.name.setText(bundle.getString("name"));
        }
    }


    private void updateInfor() {
        binding.tvUpdateInfor.setOnClickListener(view -> {
            requireActivity().getSupportFragmentManager().beginTransaction().
                    replace(R.id.container_frame, new UpdateInforFragment()).
                    addToBackStack(null).commit();
        });
    }

    private void login() {

    }

    private void exitApp() {
        binding.exit.setOnClickListener(view -> new AlertDialog.Builder(requireActivity()).setTitle("Xác nhận thoát")
                .setMessage("Bạn có muốn thoát không ?")
                .setNegativeButton("Hủy", (dialogInterface, i) -> dialogInterface.dismiss()).setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        requireActivity().finishAffinity();
                    }
                }).create().show());
    }


    private void logoutUser() {
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getToken().isEmpty()) {
                    Toast.makeText(requireContext(), "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
                } else {
                    new AlertDialog.Builder(requireActivity()).setTitle("Xác nhận thoát tài khoản")
                            .setMessage("Bạn có muốn thoát không ?")
                            .setNegativeButton("Hủy", (dialogInterface, i) -> dialogInterface.dismiss()).setPositiveButton("Thoát", (dialogInterface, i) -> {
                                sharedPref = requireActivity().getSharedPreferences(
                                        getString(R.string.preference_file_key), MODE_PRIVATE);
                                editor = sharedPref.edit();
                                editor.clear().commit();
                                checkUserNull();
                            }).create().show();
                }

            }
        });
    }

    private void checkUserNull() {
        if (getToken().isEmpty()) {
            binding.imgName.setVisibility(View.GONE);
            binding.imgUser.setVisibility(View.VISIBLE);
            binding.name.setText("Bạn");
            binding.logout.setVisibility(View.GONE);
            binding.tvUpdateInfor.setText("Đăng ký tài khoản và nhận ngay quà tặng hấp dận ");
            binding.tvUpdateInfor.setOnClickListener(view -> startActivity(new Intent(requireActivity(), LoginActivity.class)));
        } else {
            binding.imgName.setVisibility(View.VISIBLE);
            binding.imgUser.setVisibility(View.GONE);
            binding.logout.setVisibility(View.VISIBLE);
            getUserInfor();
            updateInfor();
        }
    }

    private String getToken() {
        sharedPref = requireActivity().getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
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
                    binding.imgName.setText(result.getName().substring(0, 1).toUpperCase(Locale.getDefault()));
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