package com.example.tocotoco.feature.order;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tocotoco.R;
import com.example.tocotoco.feature.cart.CartProductAdapter;
import com.example.tocotoco.model.ProductSessionModel;
import com.google.android.gms.common.util.NumberUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHorder>{
    private Context context;
    private List<ProductSessionModel> data;
//    private TranferClickListener tranferClickListener;
    ChooseItemListener chooseItemListener;
    DecimalFormat formatter = new DecimalFormat("#,###,###");
    public OrderAdapter(Context context, List<ProductSessionModel> data, ChooseItemListener chooseItemListener) {
        this.context = context;
        this.data = data;
        this.chooseItemListener = chooseItemListener;
    }

    @Override
    public OrderAdapter.OrderHorder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false);
        return new OrderAdapter.OrderHorder(view);
    }

    @Override
    public void onBindViewHolder(final OrderAdapter.OrderHorder holder, final int position) {

        ProductSessionModel productSessionModel = data.get(position);
        if (!TextUtils.isEmpty(productSessionModel.getProductName())) {
                holder.tv_name_product.setText(productSessionModel.getProductName());
        }
        if (!TextUtils.isEmpty(productSessionModel.getPrice())) {
            holder.tv_name_product.setText(productSessionModel.getProductName());
        }

        if (!TextUtils.isEmpty(productSessionModel.getPriceAfterDiscount())) {
            holder.tv_price.setText(formatter.format(Integer.parseInt(productSessionModel.getPriceAfterDiscount()))  + "đ");
        }else {
            holder.tv_old_price.setVisibility(View.GONE);
            holder.tv_price.setText(formatter.format(productSessionModel.getTotal())  + "đ");
        }

        if (!TextUtils.isEmpty(productSessionModel.getPrice())) {
            holder.tv_old_price.setText(formatter.format(Integer.parseInt(productSessionModel.getPrice()) * productSessionModel.getQuantity())  + "đ");
        }

        holder.tv_old_price.setPaintFlags(holder.tv_old_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.tv_quantity.setText(String.valueOf(productSessionModel.getQuantity()));

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);

        final int[] quantity = {productSessionModel.getQuantity()};

        Glide.with(context).load(productSessionModel.getDisplayImage()).apply(options).into(holder.img_product);

        String oldMoney = productSessionModel.getPrice();
        int money = Integer.parseInt(productSessionModel.getPriceAfterDiscount() == null? productSessionModel.getPrice() : productSessionModel.getPriceAfterDiscount());
        int moneyItem = money/productSessionModel.getQuantity();
        holder.tv_add_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity[0]++;
                chooseItemListener.OnAdd(productSessionModel.getProductId(), quantity[0]);
                holder.tv_quantity.setText(String.valueOf(quantity[0]));
                holder.tv_old_price.setText(formatter.format(Integer.parseInt(oldMoney) * quantity[0]) + "đ");
                holder.tv_price.setText(formatter.format(moneyItem * quantity[0]) + "đ");
            }
        });

        holder.tv_reduce_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quantity[0] > 0) {
                    quantity[0] --;
                    if(quantity[0] != 0) {
                        holder.tv_quantity.setText(String.valueOf(quantity[0]));
                        holder.tv_old_price.setText(formatter.format(Integer.parseInt(oldMoney) * quantity[0]) + "đ");
                        holder.tv_price.setText(formatter.format(moneyItem * quantity[0]) + "đ");
                    }
                }
                if(quantity[0] != 0) {
                    chooseItemListener.OnDel(productSessionModel.getProductId(), quantity[0]);
                }else {
                    chooseItemListener.OnDel(productSessionModel.getId(), quantity[0]);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null == data) ? 0 : data.size();
    }

    class OrderHorder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name_product)
        TextView tv_name_product;

        @BindView(R.id.tv_quantity)
        TextView tv_quantity;

        @BindView(R.id.tv_price)
        TextView tv_price;

        @BindView(R.id.img_product)
        ImageView img_product;

        @BindView(R.id.tv_reduce_quantity)
        TextView tv_reduce_quantity;

        @BindView(R.id.tv_add_quantity)
        TextView tv_add_quantity;

        @BindView(R.id.tv_old_price)
        TextView tv_old_price;


        private OrderHorder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
    public interface ChooseItemListener {
        void OnAdd(int productId, int quantity);
        void OnDel(int productId, int quantity);
    }
}
