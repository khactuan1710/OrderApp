package com.example.tocotoco.feature.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.tocotoco.R;
import com.example.tocotoco.databinding.ActivityOrderBinding;

public class OrderActivity extends AppCompatActivity{
    private ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order);
    }

}