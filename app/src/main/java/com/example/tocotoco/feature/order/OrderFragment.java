package com.example.tocotoco.feature.order;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tocotoco.R;
import com.example.tocotoco.databinding.FragmentOrderBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class OrderFragment extends Fragment {
    private FragmentOrderBinding binding;
    private int[] titles = new int[]{R.string.giao_hang, R.string.mang_di};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViewPager();
        onBackActivity();
    }

    private void onBackActivity() {
        binding.backActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });
    }

    private void setUpViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(requireActivity());
        binding.viewPager2.setAdapter(adapter);
        binding.viewPager2.setOffscreenPageLimit(1);
        new TabLayoutMediator(binding.tabLayout, binding.viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(requireContext().getResources().getString(titles[position]));
            }
        }).attach();

        for (int i = 0; i < binding.tabLayout.getTabCount(); i++) {
            TextView textView = (TextView) LayoutInflater.from(requireContext()).inflate(R.layout.custom_tab, null);
            binding.tabLayout.getTabAt(i).setCustomView(textView);
        }
    }

    static class ViewPagerAdapter extends FragmentStateAdapter {

        public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 0) {
                return new DeliveryFragment();
            } else {
                return new CarriedFragment();
            }
        }

        @Override
        public int getItemCount() {
            return 2;
        }

    }
}