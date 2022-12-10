package com.example.tocotoco.feature.orderStatus;

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
import com.example.tocotoco.feature.order.OrderAdapter;
import com.example.tocotoco.model.ProductSessionModel;
import com.example.tocotoco.model.ProductsResult;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemsOrderAdapter extends RecyclerView.Adapter<ItemsOrderAdapter.ItemsOrderHoder>{
    private Context context;
    private List<ProductsResult.ProductsResultModel> data;
    //    private TranferClickListener tranferClickListener;
    DecimalFormat formatter = new DecimalFormat("#,###,###");
    public ItemsOrderAdapter(Context context, List<ProductsResult.ProductsResultModel> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ItemsOrderHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new ItemsOrderHoder(view);
    }

    @Override
    public void onBindViewHolder(final ItemsOrderHoder holder, final int position) {

        ProductsResult.ProductsResultModel productsResultModel = data.get(position);
        if (!TextUtils.isEmpty(productsResultModel.getProductName())) {
            holder.tv_name_product.setText(productsResultModel.getQuantity() + "x " + productsResultModel.getProductName());
        }
        if (!TextUtils.isEmpty(productsResultModel.getSize())) {
            holder.tv_size.setText("Cỡ " + productsResultModel.getSize());
        }

        if (!TextUtils.isEmpty(productsResultModel.getPrice())) {
            holder.tv_price_old.setText(formatter.format(Integer.parseInt(productsResultModel.getPrice()) * productsResultModel.getQuantity())  + "đ");
        }

        if (!TextUtils.isEmpty(productsResultModel.getPriceAfterDiscount())) {
            holder.tv_price.setText(formatter.format(Integer.parseInt(productsResultModel.getTotal()))  + "đ");
        }

        holder.tv_price_old.setPaintFlags(holder.tv_price_old.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//        holder.tv_quantity.setText(String.valueOf(productSessionModel.getQuantity()));

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);



        Glide.with(context).load(productsResultModel.getDisplayImage()).apply(options).into(holder.img_product);
    }

    @Override
    public int getItemCount() {
        return (null == data) ? 0 : data.size();
    }

    class ItemsOrderHoder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name_product)
        TextView tv_name_product;

        @BindView(R.id.tv_size)
        TextView tv_size;

        @BindView(R.id.tv_price)
        TextView tv_price;

        @BindView(R.id.tv_price_old)
        TextView tv_price_old;

        @BindView(R.id.img_product)
        ImageView img_product;


        private ItemsOrderHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
