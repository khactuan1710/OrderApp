package com.example.tocotoco.feature.product_detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.tocotoco.R;
import com.example.tocotoco.databinding.ActivityProductDetailBinding;
import com.example.tocotoco.feature.order.OrderActivity;

public class ProductDetailActivity extends AppCompatActivity {
    private ActivityProductDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);
        checkTopping();
        passActivity();
    }

    private void passActivity() {
        binding.btnAddCard.setOnClickListener(view -> {
            Intent intent = new Intent(ProductDetailActivity.this, OrderActivity.class);
            startActivity(intent);
        });
    }


    private void checkTopping() {
    }

}