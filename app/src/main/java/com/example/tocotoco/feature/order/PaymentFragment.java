package com.example.tocotoco.feature.order;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.tocotoco.R;
import com.example.tocotoco.databinding.FragmentPaymentBinding;

public class PaymentFragment extends Fragment {
    private FragmentPaymentBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        backActivity();
        payCash();
        payMomo();
    }

    private void backActivity() {
        binding.backActivity.setOnClickListener(view -> getActivity().getSupportFragmentManager().popBackStack());
    }

    private void payMomo() {
        binding.payMomo.setOnClickListener(view -> {

        });
    }

    private void payCash() {
        binding.payCash.setOnClickListener(view -> {

        });
    }
}