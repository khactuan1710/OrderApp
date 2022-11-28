package com.example.tocotoco.feature.order;

import android.content.Context;
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
    DecimalFormat formatter = new DecimalFormat("#,###,###");
    public OrderAdapter(Context context, List<ProductSessionModel> data) {
        this.context = context;
        this.data = data;
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

        if (!TextUtils.isEmpty(productSessionModel.getPrice())) {
            holder.tv_price.setText(formatter.format(Integer.parseInt(productSessionModel.getPrice()))  + "đ");
        }

        holder.tv_quantity.setText(String.valueOf(productSessionModel.getQuantity()));

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);



        Glide.with(context).load(productSessionModel.getDisplayImage()).apply(options).into(holder.img_product);

//        if (null != tranferMoneyHisData.getReceiver()) {
//            String numberContactReceive = tranferMoneyHisData.getReceiver();
//            String nameContactReceive = UtilsPermissions.getNameContact(context,tranferMoneyHisData.getReceiver());
//
//
//            if (numberContactReceive.startsWith("84")) {
//                numberContactReceive = "0" + numberContactReceive.substring(2);
//                if (nameContactReceive.startsWith("84")) {
//                    nameContactReceive = "0" + nameContactReceive.substring(2);
//                }
//                nameContactReceive =  UtilsPermissions.getNameContact(context,nameContactReceive);
//            }
//            if (numberContactReceive.equals(nameContactReceive)) {
//                //          holder.mNameReceiveTv.setText("");
//            } else {
//                //        holder.mNameReceiveTv.setText(String.format("(%s)", nameContactReceive));
//            }
//            holder.mPhoneReceiveTv.setText(numberContactReceive);
//
//
//        }
//        holder.btnChonMau.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tranferClickListener.onClick(v, data.get(holder.getAdapterPosition()), holder.getAdapterPosition());
//            }
//        });
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


        private OrderHorder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
