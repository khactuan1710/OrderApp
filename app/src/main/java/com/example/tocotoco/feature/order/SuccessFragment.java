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
import com.example.tocotoco.databinding.FragmentSuccessBinding;


public class SuccessFragment extends Fragment {
    private FragmentSuccessBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_success, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        goToOrderStatusFragment();
    }

    private void goToOrderStatusFragment() {
        binding.btnFollowOrder.setOnClickListener(view -> {
            Bundle bundle=new Bundle();
            OrderStatusFragment orderStatusFragment=new OrderStatusFragment();
            orderStatusFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().
                    beginTransaction().replace(R.id.fragmentContainerViewHome, orderStatusFragment).addToBackStack(null).commit();
        });
    }

}