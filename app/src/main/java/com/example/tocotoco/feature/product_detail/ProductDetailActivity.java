package com.example.tocotoco.feature.product_detail;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.tocotoco.R;

public class ProductDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        checkTopping();
    }


    private void checkTopping() {
    }

}