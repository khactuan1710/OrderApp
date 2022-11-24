package com.example.tocotoco.feature.account;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tocotoco.databinding.ActivityUpdateInforBinding;

public class UpdateInforActivity extends AppCompatActivity {
    private ActivityUpdateInforBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateInforBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}