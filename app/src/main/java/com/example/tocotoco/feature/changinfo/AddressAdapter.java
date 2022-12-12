package com.example.tocotoco.feature.changinfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tocotoco.R;
import com.example.tocotoco.feature.order.OrderAdapter;
import com.example.tocotoco.model.CityResult;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHodel> {
    private List<CityResult.CityResult2.CityResult3> list;
    private ChooseAddressListener chooseAddressListener;

    public AddressAdapter(List<CityResult.CityResult2.CityResult3> list, ChooseAddressListener chooseAddressListener) {
        this.list = list;
        this.chooseAddressListener = chooseAddressListener;
    }

    @NonNull
    @Override
    public AddressViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address, parent, false);
        return new AddressViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHodel holder, int position) {
        CityResult.CityResult2.CityResult3 cityResult3 = list.get(position);
        holder.textView.setText(cityResult3.getName());

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseAddressListener.clickItem(cityResult3);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AddressViewHodel extends RecyclerView.ViewHolder {
        private TextView textView;


        public AddressViewHodel(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_address);
        }
    }
}
