package com.example.tocotoco.home.orderdetail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.recyclerview.widget.RecyclerView;

import com.example.tocotoco.databinding.ItemOrderDetailBinding;
import com.example.tocotoco.model.OrderDetailResult;
import com.example.tocotoco.model.OrderItemDetailResult;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class OrderItemProductAdapter extends RecyclerView.Adapter<OrderItemProductAdapter.ViewHolder> {

    private List<OrderItemDetailResult> list;

    public OrderItemProductAdapter(List<OrderItemDetailResult> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ItemOrderDetailBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        OrderItemDetailResult result=list.get(position);
        if (result==null)
            return;
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormat = NumberFormat.getNumberInstance(locale);
        holder.binding.slProduct.setText(result.getQuantity()+"x");
        holder.binding.tvName.setText(result.getProductName());
        int quantity=result.getQuantity();
        float price=Float.parseFloat(result.getPrice());
        float total=quantity*price;
        holder.binding.tvPrice.setText(currencyFormat.format(total)+"đ");
        holder.binding.tvSize.setText("Size: "+result.getSize());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemOrderDetailBinding binding;

        public ViewHolder(ItemOrderDetailBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
