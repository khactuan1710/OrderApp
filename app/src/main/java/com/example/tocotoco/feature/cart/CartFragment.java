package com.example.tocotoco.feature.cart;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tocotoco.R;
import com.example.tocotoco.feature.order.OrderActivity;
import com.example.tocotoco.model.CartInfoResult;
import com.example.tocotoco.model.ProductSessionModel;
import com.example.tocotoco.model.ProductsSessionResult;
import com.example.tocotoco.model.RegisterResult;
import com.example.tocotoco.model.UserInfoResult;
import com.example.tocotoco.network.NetWorkController;
import com.example.tocotoco.network.TCCCallback;
import com.example.tocotoco.util.EasyDialog;
import com.gemvietnam.base.viper.ViewFragment;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Response;

public class CartFragment extends ViewFragment<CartContract.Presenter> implements CartContract.View, View.OnClickListener, CartProductAdapter.ChangeItemListener, EasyDialog.EnterListenerBack {
    public static CartFragment getInstance() {
        return new CartFragment();
    }
    @BindView(R.id.ic_back)
    ImageView ic_back;
    @BindView(R.id.tv_total_money)
    TextView tv_total_money;
    @BindView(R.id.tv_del_all)
    TextView tv_del_all;
    @BindView(R.id.btn_confirm)
    TextView btn_confirm;
    @BindView(R.id.lnl_data)
    LinearLayout lnl_data;
    @BindView(R.id.lnl_no_data)
    LinearLayout lnl_no_data;
    @BindView(R.id.cv_ship)
    CardView cv_ship;
    @BindView(R.id.rcv_order)
    RecyclerView rcv_order;
    private int sessionId;
    private Intent intent;
    private String token;
    SharedPreferences sharedPref;
    Response<UserInfoResult> userData;
    CartProductAdapter cartProductAdapter;
    List<ProductSessionModel> list;
    DecimalFormat formatter = new DecimalFormat("#,###,###");

    @Override
    public void initLayout() {
        super.initLayout();
        setListener();
        initData();
    }

    private void initData() {
        intent = getActivity().getIntent();
        token = intent.getStringExtra("tokenToCart");
        sharedPref = getViewContext().getSharedPreferences(requireContext().getString(R.string.preference_file_key), MODE_PRIVATE);
        sessionId = sharedPref.getInt(requireContext().getString(R.string.session_id), 0);
        mPresenter.itemsInShoppingSession(token, sessionId);
        mPresenter.getCartInfo(token, sessionId);
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
            case R.id.tv_del_all:
                    AlertDialog.Builder alert  = new AlertDialog.Builder(getViewContext());
                    alert.setTitle("Xác nhận xoá");
                    alert.setMessage("Bạn chắc chắn xoá đơn hàng?");
                    alert.setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            delOrder();
                        }
                    });
                    alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alert.create().show();
                break;
//            case R.id.imgClearText:
//                ed_note.setText("");
//                break;
        }
    }

    private void delOrder() {
        NetWorkController.deleteShoppingSession(new TCCCallback<RegisterResult>() {
            @Override
            public void onTCTCSuccess(Call<RegisterResult> call, Response<RegisterResult> response) {
                if(response.body().getIsSuccess()) {
                    getCartInfoFail();
                    rcv_order.setVisibility(View.GONE);
                }else {
                    Toast.makeText(getViewContext(), "Lỗi hệ thống", Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onTCTCFailure(Call<RegisterResult> call) {

            }
        }, token, sessionId);
    }

    private void confirmOrder() {
        Intent i2;
        i2 = new Intent(getViewContext(), OrderActivity.class);
        i2.putExtra("tokenToOrder", token);
        startActivity(i2);
//        getViewContext().finish();
    }

    private void setListener() {
        ic_back.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
        tv_del_all.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.cart_fragment;
    }

    @Override
    public void initViewDetail(Response<ProductsSessionResult> data) {
        list = data.body().getResults();
        cartProductAdapter = new CartProductAdapter(getViewContext(), list, this);
        rcv_order.setAdapter(cartProductAdapter);
        rcv_order.setLayoutManager(new LinearLayoutManager(getViewContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.itemsInShoppingSession(token, sessionId);
        mPresenter.getCartInfo(token, sessionId);
    }



    @Override
    public void getCartInfoSuccess(Response<CartInfoResult> data) {
        if(data.body().getResults().getPriceAfterDiscount() != null) {
            tv_total_money.setText("Tổng cộng: " + formatter.format(Integer.parseInt(data.body().getResults().getPriceAfterDiscount()))  + "đ");
            btn_confirm.setText("Giao hàng -" + formatter.format(Integer.parseInt(data.body().getResults().getPriceAfterDiscount())) + "đ");
        }
    }

    @Override
    public void getCartInfoFail() {
        tv_total_money.setText("0đ");
        btn_confirm.setText("Giao hàng -0đ");
        EasyDialog easyDialog = new EasyDialog(getViewContext(), this);
        easyDialog.show(getFragmentManager(), "");
    }

    @Override
    public void AddItem(int productId, int quantity) {
        mPresenter.addItemToShoppingSession(token, sessionId, productId, quantity, "M");
    }

    @Override
    public void DelItem(int productId, int quantity) {
        if(quantity == 0) {
            mPresenter.deleteItemInShoppingSession(token, productId, sessionId);
        }else {
            mPresenter.addItemToShoppingSession(token, sessionId, productId, quantity, "M");
        }
    }

    @Override
    public void onClose() {
        getViewContext().finish();
    }
}
