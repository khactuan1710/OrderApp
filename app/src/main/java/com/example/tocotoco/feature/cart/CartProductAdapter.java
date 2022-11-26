package com.example.tocotoco.feature.cart;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tocotoco.R;
import com.example.tocotoco.model.ProductSessionModel;
import com.gemvietnam.base.log.Logger;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartProductAdapter extends
        RecyclerView.Adapter<CartProductAdapter.CartProductHolder>{
    private Context context;
    private ArrayList<ProductSessionModel> data;
//    private TranferClickListener tranferClickListener;

    public CartProductAdapter(Context context, ArrayList<ProductSessionModel> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public CartProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new CartProductHolder(view);
    }

    @Override
    public void onBindViewHolder(final CartProductHolder holder, final int position) {

//        TranferMoneyHisData tranferMoneyHisData = data.get(position);
//        if (!TextUtils.isEmpty(tranferMoneyHisData.getAmount())) {
//            try {
//                holder.mMoneyTv.setText(String.format("%sđ",
//                        NumberUtils.formatViettel(Integer.valueOf(tranferMoneyHisData.getAmountInt()))));
//            } catch (Exception e) {
//                Logger.w(e);
//                holder.mMoneyTv.setText("");
//            }
//        }
//        if (!TextUtils.isEmpty(tranferMoneyHisData.getFee())) {
//            try {
//                //      holder.mMoneyFeeTv.setText(String.format("(Phí: %sđ)", NumberUtils.formatViettel(Integer.valueOf(tranferMoneyHisData.getFee()))));
//            } catch (Exception e) {
//                Logger.w(e);
//                //         holder.mMoneyFeeTv.setText("");
//            }
//        }
//        if (!TextUtils.isEmpty(tranferMoneyHisData.getDate())) {
//            holder.mTimeTv.setText(TimeUtils.convertDateToDate(tranferMoneyHisData.getDate(), "hh:mm:ss dd-MM-yyyy ", "hh:mm dd/MM/yyyy ").replace("-","/"));
//        }
//
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

    class CartProductHolder extends RecyclerView.ViewHolder {

//        @BindView(R.id.phone_receive_tv)
//        TextView mPhoneReceiveTv;

//        @BindView(R.id.name_receive_tv)
//        TextView mNameReceiveTv;

//        @BindView(R.id.money_tv)
//        TextView mMoneyTv;

//        @BindView(R.id.money_fee_tv)
//        TextView mMoneyFeeTv;

//        @BindView(R.id.time_tv)
//        TextView mTimeTv;

        private CartProductHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
