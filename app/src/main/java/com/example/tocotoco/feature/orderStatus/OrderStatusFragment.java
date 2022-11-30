package com.example.tocotoco.feature.orderStatus;

import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tocotoco.R;
import com.example.tocotoco.feature.order.OrderAdapter;
import com.example.tocotoco.feature.registerAcc.RegisterAccountContract;
import com.example.tocotoco.feature.registerAcc.RegisterAccountFragment;
import com.example.tocotoco.model.ProductSessionModel;
import com.example.tocotoco.model.ProductsResult;
import com.example.tocotoco.model.RegisterResult;
import com.example.tocotoco.model.UserCurrentResult;
import com.gemvietnam.base.viper.ViewFragment;

import java.util.List;

import butterknife.BindView;
import retrofit2.Response;

public class OrderStatusFragment extends ViewFragment<OrderStatusContract.Presenter> implements OrderStatusContract.View, View.OnClickListener{
    @BindView(R.id.tv_start_price)
    TextView tv_start_price;
    @BindView(R.id.tv_end_price)
    TextView tv_end_price;
    @BindView(R.id.btn_confirm)
    Button btn_confirm;
    @BindView(R.id.rcv_order)
    RecyclerView rcv_order;
    private String token;
    SharedPreferences sharedPref;
    ItemsOrderAdapter itemsOrderAdapter;
    List<ProductsResult.ProductsResultModel> list;
    public static OrderStatusFragment getInstance() {
        return new OrderStatusFragment();
    }

    @Override
    public void initLayout() {
        super.initLayout();
        setListener();
        initData();
    }

    private void setListener() {
        btn_confirm.setOnClickListener(this);
    }

    private void initData() {
        sharedPref = getViewContext().getSharedPreferences(requireContext().getString(R.string.preference_file_key), MODE_PRIVATE);
        token = sharedPref.getString(requireContext().getString(R.string.preference_key_token), "");
        mPresenter.getUserCurrentOrder(token);
        tv_start_price.setPaintFlags(tv_start_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm:
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + "18008168"));
                if (ContextCompat.checkSelfPermission(getViewContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                    ActivityCompat.requestPermissions(getViewContext(), new String[]{Manifest.permission.CALL_PHONE}, 1);
                else
                    startActivity(intent);
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.order_status_fragment;
    }

    @Override
    public void getUserCurrentOrderSuccess(UserCurrentResult userCurrentResult) {
        mPresenter.getItemsInOrder(token, userCurrentResult.getResults().getOrderId());
    }

    @Override
    public void getItemsInOrderSuccess(ProductsResult productsResult) {
        list = productsResult.getResults();
        itemsOrderAdapter = new ItemsOrderAdapter(getViewContext(), list);
        rcv_order.setAdapter(itemsOrderAdapter);
        rcv_order.setLayoutManager(new LinearLayoutManager(getViewContext()));
    }
}
