package com.example.tocotoco.feature.account.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tocotoco.R;
import com.example.tocotoco.databinding.ActivityAccountBinding;
import com.example.tocotoco.feature.account.fragment.ListStoreFragment;


public class AccountActivity extends AppCompatActivity {
    private ActivityAccountBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}