package com.example.tocotoco.feature.order;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tocotoco.R;
import com.example.tocotoco.feature.product_detail.ProductDetailContract;
import com.example.tocotoco.feature.product_detail.ProductDetailFragment;
import com.example.tocotoco.feature.registerAcc.RegisterAccountActivity;
import com.example.tocotoco.home.activityhome.HomeActivity;
import com.example.tocotoco.model.CartInfoResult;
import com.example.tocotoco.model.ProductResult;
import com.example.tocotoco.model.ProductSessionModel;
import com.example.tocotoco.model.ProductsSessionResult;
import com.example.tocotoco.model.RegisterResult;
import com.example.tocotoco.model.UserInfoResult;
import com.gemvietnam.base.viper.ViewFragment;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import retrofit2.Response;

public class OrderFragment extends ViewFragment<OrderContract.Presenter> implements OrderContract.View, View.OnClickListener{
    @BindView(R.id.ic_back)
    ImageView ic_back;
    @BindView(R.id.rcv_order)
    RecyclerView rcv_order;
    SharedPreferences sharedPref;
    @BindView(R.id.tv_total_money)
    TextView tv_total_money;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.btn_confirm)
    Button btn_confirm;
    @BindView(R.id.ed_note)
    EditText ed_note;
    @BindView(R.id.imgClearText)
    AppCompatImageView imgClearText;
    String address;
    private int sessionId;
    private Intent intent;
    private String token;
    Response<UserInfoResult> userData;
    OrderAdapter orderAdapter;
    List<ProductSessionModel> list;
    DecimalFormat formatter = new DecimalFormat("#,###,###");
    public static OrderFragment getInstance() {
        return new OrderFragment();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                mPresenter.back();
                break;
            case R.id.btn_confirm:
                confirmOrder();
                break;
            case R.id.imgClearText:
                ed_note.setText("");
                break;
        }
    }

    private void confirmOrder() {
        String note = "";
        if(!ed_note.getText().toString().isEmpty()) {
            note = ed_note.getText().toString();
        }
        mPresenter.confirmOrder(token, sessionId, "Tiền mặt", userData.body().getResults().getPhonenumber(), userData.body().getResults().getAddress(), note);
    }

    @Override
    public void initLayout() {
        super.initLayout();
        setListener();
        initData();
    }
    private void setListener() {
       ic_back.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
    }
    private void initData() {
        intent = getActivity().getIntent();
        token = intent.getStringExtra("tokenToOrder");
        sharedPref = getViewContext().getSharedPreferences(requireContext().getString(R.string.preference_file_key), MODE_PRIVATE);
        address = sharedPref.getString(requireContext().getString(R.string.address), "");
        tv_address.setText(address);
        sessionId = sharedPref.getInt(requireContext().getString(R.string.session_id), 0);
        mPresenter.getUserInfo(token);
        mPresenter.itemsInShoppingSession(token, sessionId);
        mPresenter.getUserInfo(token);
        mPresenter.getCartInfo(token, sessionId);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_detail;
    }

    @Override
    public void initViewDetail(Response<ProductsSessionResult> data) {
        list = data.body().getResults();
        orderAdapter = new OrderAdapter(getViewContext(), list);
        rcv_order.setAdapter(orderAdapter);
        rcv_order.setLayoutManager(new LinearLayoutManager(getViewContext()));
    }

    @Override
    public void getCartInfoSuccess(Response<CartInfoResult> data) {
        tv_total_money.setText(formatter.format(Integer.parseInt(data.body().getResults().getPriceBeforeDiscount()))  + "đ");
    }

    @Override
    public void getUserInfoSuccess(Response<UserInfoResult> data) {
        userData =data;
        tv_name.setText(data.body().getResults().getName());
        tv_phone.setText(data.body().getResults().getPhonenumber());
    }

    @Override
    public void confirmOrderSuccess(Response<RegisterResult> data) {
        Intent i = new Intent(getViewContext(), ConfirmSuccessOrderActivty.class);
        startActivity(i);
    }
}