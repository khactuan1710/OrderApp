package com.example.tocotoco.feature.order;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tocotoco.R;
import com.example.tocotoco.databinding.FragmentDeliveryBinding;

public class DeliveryFragment extends Fragment {
    private FragmentDeliveryBinding binding;
    private OrderActivity orderActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_delivery, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        orderActivity = (OrderActivity) getActivity();
        goToPaymentFragment();
        goToOrderStatusFragment();
    }

    private void goToOrderStatusFragment() {
        binding.btnOrder.setOnClickListener(v -> orderActivity.goToOrderStatusFragment());
    }

    private void goToPaymentFragment() {
        binding.btnPayment.setOnClickListener(view -> orderActivity.goToPaymentFragment());
    }
}