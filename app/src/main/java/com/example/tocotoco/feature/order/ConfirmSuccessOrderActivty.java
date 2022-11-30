package com.example.tocotoco.feature.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tocotoco.R;
import com.example.tocotoco.feature.orderStatus.OrderStatusActivity;
import com.example.tocotoco.home.activityhome.HomeActivity;
import com.example.tocotoco.model.ProductSessionModel;

import java.util.ArrayList;

public class ConfirmSuccessOrderActivty extends AppCompatActivity {

    Button btnflow, btnBackHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_success_order_activty);
        btnflow = findViewById(R.id.btn_flow);
        btnBackHome = findViewById(R.id.btn_back_home);

        btnflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), OrderStatusActivity.class);
                startActivity(i);
            }
        });
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
            }
        });
    }
}