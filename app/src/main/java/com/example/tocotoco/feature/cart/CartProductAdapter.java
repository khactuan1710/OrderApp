package com.example.tocotoco.feature.cart;

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
import com.example.tocotoco.model.ProductSessionModel;
import com.gemvietnam.base.log.Logger;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.CartProductHolder>{
    private Context context;
    private List<ProductSessionModel> data;
    ChangeItemListener changeItemListener;
//    private TranferClickListener tranferClickListener;
    DecimalFormat formatter = new DecimalFormat("#,###,###");

    public CartProductAdapter(Context context, List<ProductSessionModel> data, ChangeItemListener changeItemListener) {
        this.context = context;
        this.data = data;
        this.changeItemListener = changeItemListener;
    }

    @Override
    public CartProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new CartProductHolder(view);
    }

    @Override
    public void onBindViewHolder(final CartProductHolder holder, final int position) {

        ProductSessionModel productSessionModel = data.get(position);
        if (!TextUtils.isEmpty(productSessionModel.getProductName())) {
            holder.tv_name_product.setText(productSessionModel.getProductName());
        }
        if (!TextUtils.isEmpty(productSessionModel.getPriceAfterDiscount())) {
            holder.tv_price.setText(formatter.format(Integer.parseInt(productSessionModel.getPriceAfterDiscount()))  + "đ");
        }

        if (!TextUtils.isEmpty(productSessionModel.getPrice())) {
            holder.tv_old_price.setText(formatter.format(Integer.parseInt(productSessionModel.getPrice()))  + "đ");
        }

//        tv_start_price.setPaintFlags(tv_start_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.tv_quantity.setText(String.valueOf(productSessionModel.getQuantity()));

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);

        final int[] quantity = {productSessionModel.getQuantity()};

        Glide.with(context).load(productSessionModel.getDisplayImage()).apply(options).into(holder.img_product);


        holder.tv_add_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity[0]++;
                changeItemListener.AddItem(productSessionModel.getProductId(), quantity[0]);
                holder.tv_quantity.setText(String.valueOf(quantity[0]));
            }
        });

        holder.tv_reduce_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quantity[0] > 0) {
                    quantity[0] --;
                    if(quantity[0] != 0) {
                        holder.tv_quantity.setText(String.valueOf(quantity[0]));
                    }
                }
                if(quantity[0] != 0) {
                    changeItemListener.DelItem(productSessionModel.getProductId(), quantity[0]);
                }else {
                    changeItemListener.DelItem(productSessionModel.getId(), quantity[0]);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null == data) ? 0 : data.size();
    }

    class CartProductHolder extends RecyclerView.ViewHolder {

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

        private CartProductHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
    public interface  ChangeItemListener {
        void AddItem(int productId, int quantity);
        void DelItem(int productId, int quantity);
    }
}
