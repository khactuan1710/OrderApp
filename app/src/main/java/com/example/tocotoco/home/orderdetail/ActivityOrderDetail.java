package com.example.tocotoco.home.orderdetail;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tocotoco.R;
import com.example.tocotoco.databinding.ActivityOrderDetailBinding;
import com.example.tocotoco.dialog.DialogUtils;
import com.example.tocotoco.feature.order.ConfirmSuccessOrderActivty;
import com.example.tocotoco.home.orderdetail.adapter.OrderItemProductAdapter;
import com.example.tocotoco.home.orderdetail.network.ApiService;
import com.example.tocotoco.model.CreateOrder;
import com.example.tocotoco.model.OrderDetail;
import com.example.tocotoco.model.OrderItemDetail;
import com.example.tocotoco.model.OrderItemDetailResult;
import com.example.tocotoco.model.RegisterResult;
import com.example.tocotoco.network.NetWorkController;
import com.example.tocotoco.network.TCCCallback;
import com.example.tocotoco.util.Utils;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class ActivityOrderDetail extends AppCompatActivity {
    private ActivityOrderDetailBinding binding;
    SharedPreferences sharedPref;
    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    Locale locale = new Locale("vi", "VN");
    NumberFormat currencyFormat = NumberFormat.getNumberInstance(locale);
    private List<OrderItemDetailResult> list = new ArrayList<>();
    private OrderItemProductAdapter adapter;
    Context context;
    String method = "";
    String totalPay = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        //zalo
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ZaloPay SDK Init
        ZaloPaySDK.init(2553, Environment.SANDBOX);
        binding.backActivity.setOnClickListener(view -> onBackPressed());
        binding.btnReSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert  = new AlertDialog.Builder(context);
                alert.setTitle("Xác nhận đặt lại");
                alert.setMessage("Bạn chắc chắn muốn đặt đơn hàng?");
                alert.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sendOrder();
                    }
                });
                alert.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.create().show();
            }
        });
        getDataOrderDetail();
        getDataOrderItemDetail();
        Log.e("ID_Order", "" + getOrderID());
    }

    private void sendOrder() {

        DialogUtils.showProgressDialog(this);
        if(method.equals("Zalo Pay")) {
            CreateOrder orderApi = new CreateOrder();
            try {
                JSONObject data = orderApi.createOrder(totalPay);
                String code = data.getString("return_code");
                String tokenZalo = "";
                if (code.equals("1")) {
                    tokenZalo = data.getString("zp_trans_token");
                }
                ZaloPaySDK.getInstance().payOrder((Activity) context, tokenZalo, "demozpdk://app", new PayOrderListener() {
                    @Override
                    public void onPaymentSucceeded(String s, String s1, String s2) {
                        String noteZ = "";
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
        }else {
            NetWorkController.reOrder(new TCCCallback<RegisterResult>() {
                @Override
                public void onTCTCSuccess(Call<RegisterResult> call, Response<RegisterResult> response) {
                    if(response.body().getIsSuccess()) {
                        Intent i = new Intent(getApplicationContext(), ConfirmSuccessOrderActivty.class);
                        i.putExtra("fromDetail", "tienmat");
                        startActivity(i);
                        finish();
                    }else {
                        Toast.makeText(context, response.body().getErrorMessage(), Toast.LENGTH_LONG).show();
                    }
                    DialogUtils.dismissProgressDialog();
                }

                @Override
                public void onTCTCFailure(Call<RegisterResult> call) {
                    DialogUtils.dismissProgressDialog();
                }
            }, getToken(), getOrderID(), "");
        }

    }

    private int getOrderID() {
        return getIntent().getIntExtra("idProduct", 0);
    }


    private String getToken() {
        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
        Log.e("Token", sharedPref.getString(this.getString(R.string.preference_key_token), ""));
        return sharedPref.getString(this.getString(R.string.preference_key_token), "");
    }

    private void getDataOrderDetail() {
        DialogUtils.showProgressDialog(this);
        ApiService.API_SERVICE.getOrderDetail(getToken(), getOrderID()).enqueue(new Callback<OrderDetail>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<OrderDetail> call, Response<OrderDetail> response) {

                if (response.body() == null) {
                    return;
                }
                OrderDetail orderDetail = response.body();
//                Date date = new Date(orderDetail.getResult().getCreateat());
//                String ngay = format.format(date);
                binding.tvTime.setText(orderDetail.getResult().getCreateat());
                binding.tvAddress.setText(orderDetail.getResult().getAddress());
                binding.btnOrderStatus.setText(orderDetail.getResult().getStatus());
                binding.tvQuantity.setText("Tạm tính (" + orderDetail.getResult().getTotalProduct() + "món)");
                binding.tvTotal.setText(currencyFormat.format(Integer.parseInt(orderDetail.getResult().getTotal())) + "đ");
                binding.tvPayment.setText(currencyFormat.format(Integer.parseInt(orderDetail.getResult().getTotal())) + "đ" + "(" + orderDetail.getResult().getProvider() + ")");
                method = orderDetail.getResult().getProvider();
                totalPay = orderDetail.getResult().getTotal();
                DialogUtils.dismissProgressDialog();
            }

            @Override
            public void onFailure(Call<OrderDetail> call, Throwable t) {

            }
        });
    }

    private void getDataOrderItemDetail() {
        DialogUtils.showProgressDialog(this);
        ApiService.API_SERVICE.getOrderItemDetail(getToken(), getOrderID()).enqueue(new Callback<OrderItemDetail>() {
            @Override
            public void onResponse(Call<OrderItemDetail> call, Response<OrderItemDetail> response) {
                if (response.body() == null) {
                    return;
                }
                OrderItemDetail orderItemDetail = response.body();
                list.addAll(orderItemDetail.getResult());
                setUpRecyclerView();
                DialogUtils.dismissProgressDialog();
            }

            @Override
            public void onFailure(Call<OrderItemDetail> call, Throwable t) {

            }
        });
    }

    private void setUpRecyclerView() {
        adapter = new OrderItemProductAdapter(list);
        binding.rvOrderDetail.setHasFixedSize(true);
        binding.rvOrderDetail.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.rvOrderDetail.setLayoutManager(new LinearLayoutManager(this));
        binding.rvOrderDetail.setAdapter(adapter);
    }
}