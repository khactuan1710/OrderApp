package com.example.tocotoco.feature.order;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.tocotoco.R;
import com.example.tocotoco.feature.product_detail.ProductDetailContract;
import com.example.tocotoco.feature.product_detail.ProductDetailFragment;
import com.example.tocotoco.feature.registerAcc.RegisterAccountActivity;
import com.example.tocotoco.model.ProductResult;
import com.example.tocotoco.model.ProductsSessionResult;
import com.gemvietnam.base.viper.ViewFragment;

import butterknife.BindView;
import retrofit2.Response;

public class OrderFragment extends ViewFragment<OrderContract.Presenter> implements OrderContract.View, View.OnClickListener{
    @BindView(R.id.ic_back)
    ImageView ic_back;
    SharedPreferences sharedPref;
    String address;
    private int sessionId;
    private Intent intent;
    private String token;
    public static OrderFragment getInstance() {
        return new OrderFragment();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                mPresenter.back();
                break;
        }
    }

    @Override
    public void initLayout() {
        super.initLayout();
        setListener();
        initData();
    }
    private void setListener() {
       ic_back.setOnClickListener(this);
    }
    private void initData() {
        intent = getActivity().getIntent();
        token = intent.getStringExtra("tokenToOrder");
        sharedPref = getViewContext().getSharedPreferences(requireContext().getString(R.string.preference_file_key), MODE_PRIVATE);
        address = sharedPref.getString(requireContext().getString(R.string.address), "");
        sessionId = sharedPref.getInt(requireContext().getString(R.string.session_id), 0);
        mPresenter.getUserInfo(token);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_detail;
    }

    @Override
    public void initViewDetail(Response<ProductsSessionResult> data) {

    }
}