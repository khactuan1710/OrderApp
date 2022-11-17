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

import com.example.tocotoco.R;
import com.example.tocotoco.databinding.FragmentOrderStatusBinding;
import com.example.tocotoco.model.Status;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class OrderStatusFragment extends Fragment {
    private FragmentOrderStatusBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_status, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        backFragment();
        setUpViewPager();
    }

    private void setUpViewPager() {
        List<Status> list = getListStatus();
        ViewPagerAdapter adapter = new ViewPagerAdapter(requireActivity(), list);
        binding.viewPager2.setAdapter(adapter);

        new TabLayoutMediator(binding.tabLayout, binding.viewPager2, (tab, position) -> tab.setText(Status.values()[position].getStatus())).attach();

        for (int i = 0; i < binding.tabLayout.getTabCount(); i++) {
            binding.tabLayout.getTabAt(i).setCustomView(R.layout.custom_tab2);
        }
    }

    private List<Status> getListStatus() {
        Bundle bundle=this.getArguments();
        List<Status> list = new ArrayList<>();
        for (int i = 0; i < Status.values().length; i++) {
            if (bundle!=null){
                list.add(Status.values()[i]);
            }else {
                if (!Status.values()[i].getStatus().equals(Status.DA_DEN_NOI.getStatus())) {
                    list.add(Status.values()[i]);
                }
            }
        }
        return list;
    }

    private void backFragment() {
        binding.backActivity.setOnClickListener(view -> getActivity().getSupportFragmentManager().popBackStack());
    }

    static class ViewPagerAdapter extends FragmentStateAdapter {
        private List<Status> statusList;

        public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Status> statusList) {
            super(fragmentActivity);
            this.statusList = statusList;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return new OrderDetailFragment();
        }

        @Override
        public int getItemCount() {
            if (statusList != null) {
                return statusList.size();
            } else {
                return 0;
            }
        }
    }
}