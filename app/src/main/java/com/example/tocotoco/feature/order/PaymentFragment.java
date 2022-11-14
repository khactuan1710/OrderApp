package com.example.tocotoco.feature.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.tocotoco.databinding.ActivityPaymentBinding;

import com.example.tocotoco.R;

public class PaymentActivity extends AppCompatActivity {
    private ActivityPaymentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_payment);
        backActivity();
        payCash();
        payMomo();
    }

    private void backActivity() {
        binding.backActivity.setOnClickListener(view -> onBackPressed());
    }

    private void payMomo() {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }

    private void payCash() {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }
}