package com.example.tocotoco.feature.order;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tocotoco.R;
import com.example.tocotoco.databinding.FragmentCarriedBinding;

public class CarriedFragment extends Fragment {
    private FragmentCarriedBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_carried, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        goToFragment();
        goToOrderStatusFragment();
    }

    private void goToOrderStatusFragment() {
        binding.btnOrder.setOnClickListener(view -> getActivity().getSupportFragmentManager().
                beginTransaction().replace(R.id.fragmentContainerViewHome, new OrderStatusFragment()).addToBackStack(null).commit());
    }

    private void goToFragment() {
        binding.btnPayment.setOnClickListener(view ->
                getActivity().getSupportFragmentManager().
                        beginTransaction().replace(R.id.fragmentContainerViewHome, new PaymentFragment()).addToBackStack(null).commit());
    }
}