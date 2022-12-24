package com.example.tocotoco.feature.order;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tocotoco.R;
import com.example.tocotoco.feature.changinfo.ChangeInfoActivity;
import com.example.tocotoco.feature.product_detail.ProductDetailContract;
import com.example.tocotoco.feature.product_detail.ProductDetailFragment;
import com.example.tocotoco.feature.registerAcc.RegisterAccountActivity;
import com.example.tocotoco.home.activityhome.HomeActivity;
import com.example.tocotoco.model.CartInfoResult;
import com.example.tocotoco.model.CreateOrder;
import com.example.tocotoco.model.ProductResult;
import com.example.tocotoco.model.ProductSessionModel;
import com.example.tocotoco.model.ProductsSessionResult;
import com.example.tocotoco.model.RegisterResult;
import com.example.tocotoco.model.UserInfoResult;
import com.example.tocotoco.util.EasyDialog;
import com.gemvietnam.base.viper.ViewFragment;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import retrofit2.Response;
import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class OrderFragment extends ViewFragment<OrderContract.Presenter> implements OrderContract.View, View.OnClickListener, OrderAdapter.ChooseItemListener , EasyDialog.EnterListenerBack {
    @BindView(R.id.ic_back)
    ImageView ic_back;
    @BindView(R.id.img_change_info)
    ImageView img_change_info;
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
    @BindView(R.id.tv_total_item)
    TextView tv_total_item;
    @BindView(R.id.tv_total_sub_money)
    TextView tv_total_sub_money;
    @BindView(R.id.btn_confirm)
    Button btn_confirm;
    @BindView(R.id.ed_note)
    EditText ed_note;
    @BindView(R.id.imgClearText)
    AppCompatImageView imgClearText;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.radio_cash)
    RadioButton radio_cash;
    @BindView(R.id.radio_zalo_pay)
    RadioButton radio_zalo_pay;
    String address;
    private int sessionId;
    private Intent intent;
    private String token;
    private String methodPay = "Tiền mặt";
    private String username;
    private String address2;

    String totalPay = "10000";
    private String nameFromChangeInfo = "";
    private String phoneFromChangeInfo = "";
    private String addressFromChangeInfo = "";


    Response<UserInfoResult> userData;
    OrderAdapter orderAdapter;
    List<ProductSessionModel> list;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPref2;
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
            case R.id.img_change_info:
                Intent i = new Intent(getViewContext(), ChangeInfoActivity.class);
                i.putExtra("nameFromOrderFragment", tv_name.getText().toString());
                i.putExtra("phoneFromOrderFragment", tv_phone.getText().toString());
                startActivity(i);
                getViewContext().finish();
                break;
        }
    }

    private void confirmOrder() {
        String note = "";
        if(!ed_note.getText().toString().isEmpty()) {
            note = ed_note.getText().toString();
        }
        editor.putString("noteOrder", note);
        editor.putString("usernameOrder", username);
        editor.putString("addressOrder", address2);
        editor.apply();
        if(methodPay.equals("Tiền mặt")) {
            mPresenter.confirmOrder(token, sessionId, methodPay, userData.body().getResults().getPhonenumber(), userData.body().getResults().getAddress(), note);
        }else {
            CreateOrder orderApi = new CreateOrder();
            try {
                JSONObject data = orderApi.createOrder(totalPay);
                String code = data.getString("return_code");
                String tokenZalo = "";
                if (code.equals("1")) {
                    tokenZalo = data.getString("zp_trans_token");
                }
                ZaloPaySDK.getInstance().payOrder(getViewContext(), tokenZalo, "demozpdk://app", new PayOrderListener() {
                    @Override
                    public void onPaymentSucceeded(String s, String s1, String s2) {
                        String noteZ = "";
                        if(!ed_note.getText().toString().isEmpty()) {
                            noteZ = ed_note.getText().toString();
                        }
                        mPresenter.confirmOrder(token, sessionId, methodPay, userData.body().getResults().getPhonenumber(), userData.body().getResults().getAddress(), noteZ);
                    }

                    @Override
                    public void onPaymentCanceled(String s, String s1) {
                        Log.e("Log", "Log cancel");
                    }

                    @Override
                    public void onPaymentError(ZaloPayError zaloPayError, String s, String s1) {
                        Log.e("Log", "Log err zalo");
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
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
        btn_confirm.setOnClickListener(this);
        img_change_info.setOnClickListener(this);
    }
    private void initData() {
        intent = getActivity().getIntent();

        sharedPref2 = getViewContext().getSharedPreferences(getViewContext().getString(R.string.preference_file_key), MODE_PRIVATE);
        editor = sharedPref2.edit();

        nameFromChangeInfo = intent.getStringExtra("nameFromOrderChangeInfo");
        phoneFromChangeInfo = intent.getStringExtra("phoneFromOrderChangeInfo");
        addressFromChangeInfo = intent.getStringExtra("addressFromOrderChangeInfo");


        sharedPref = getViewContext().getSharedPreferences(requireContext().getString(R.string.preference_file_key), MODE_PRIVATE);
        address = sharedPref.getString(requireContext().getString(R.string.address), "");
        token = sharedPref.getString(this.getString(R.string.preference_key_token), "");
        if(addressFromChangeInfo != null) {
            tv_address.setText(addressFromChangeInfo);
            address2 =addressFromChangeInfo;
        }else {
            tv_address.setText(address);
            address2 =address;
        }
        sessionId = sharedPref.getInt(requireContext().getString(R.string.session_id), 0);
//        mPresenter.getUserInfo(token);
        mPresenter.itemsInShoppingSession(token, sessionId);
        mPresenter.getUserInfo(token);
        mPresenter.getCartInfo(token, sessionId);


        //zalo
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ZaloPay SDK Init
        ZaloPaySDK.init(2553, Environment.SANDBOX);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.radio_cash) {
                    methodPay = "Tiền mặt";
                }else if (i == R.id.radio_zalo_pay) {
                    methodPay = "Zalo Pay";
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_detail;
    }

    @Override
    public void initViewDetail(Response<ProductsSessionResult> data) {
        list = data.body().getResults();
        orderAdapter = new OrderAdapter(getViewContext(), list, this);
        rcv_order.setAdapter(orderAdapter);
        rcv_order.setLayoutManager(new LinearLayoutManager(getViewContext()));
    }

    @Override
    public void getCartInfoSuccess(Response<CartInfoResult> data) {
        if(data.body().getResults().getPriceAfterDiscount() != null) {
            totalPay = data.body().getResults().getPriceAfterDiscount();
            tv_total_money.setText(formatter.format(Integer.parseInt(data.body().getResults().getPriceAfterDiscount()))  + "đ");
            tv_total_sub_money.setText(formatter.format(Integer.parseInt(data.body().getResults().getPriceAfterDiscount()))  + "đ");
        }
        tv_total_item.setText("Số tiền thanh toán(" + data.body().getResults().getTotalQuantity()+" món)");
    }

    @Override
    public void getCartInfoFail() {
        tv_total_money.setText("0đ");
        tv_total_sub_money.setText("0đ");
        tv_total_item.setText("Số tiền thanh toán(0 món)");
        EasyDialog easyDialog = new EasyDialog(getViewContext(), this);
        easyDialog.show(getFragmentManager(), "");
    }

    @Override
    public void getUserInfoSuccess(Response<UserInfoResult> data) {
        userData =data;
        if(nameFromChangeInfo != null) {
            tv_name.setText(nameFromChangeInfo);
            tv_phone.setText(phoneFromChangeInfo);
            username = nameFromChangeInfo;
        }else {
            tv_name.setText(data.body().getResults().getName());
            username = data.body().getResults().getName();
            tv_phone.setText(data.body().getResults().getPhonenumber());
        }
    }

    @Override
    public void confirmOrderSuccess(Response<RegisterResult> data) {
        Intent i = new Intent(getViewContext(), ConfirmSuccessOrderActivty.class);
        startActivity(i);
        getViewContext().finish();
    }

    @Override
    public void OnAdd(int productId, int quantity) {
        mPresenter.addItemToShoppingSession(token, sessionId, productId, quantity, "M");
    }

    @Override
    public void OnDel(int productId, int quantity) {
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