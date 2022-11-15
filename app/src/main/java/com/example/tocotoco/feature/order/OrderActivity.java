package com.example.tocotoco.feature.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.tocotoco.R;
import com.example.tocotoco.databinding.ActivityOrderBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class OrderActivity extends AppCompatActivity {
    private ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order);
    }

    public void goToPaymentFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewHome,new PaymentFragment()).addToBackStack("paymentFragment").commit();
    }

    public void goToOrderStatusFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewHome,new OrderStatusFragment()).addToBackStack("orderStatusFragment").commit();
    }

    public void goToPayStatusFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewHome,new PayStatusFragment()).addToBackStack("payStatusFragment").commit();
    }

    public void backFragment(){
        getSupportFragmentManager().popBackStack();
    }

}