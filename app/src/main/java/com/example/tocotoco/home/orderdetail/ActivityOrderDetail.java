package com.example.tocotoco.home.orderdetail;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.tocotoco.R;
import com.example.tocotoco.databinding.ActivityOrderDetailBinding;
import com.example.tocotoco.dialog.DialogUtils;
import com.example.tocotoco.home.orderdetail.adapter.OrderItemProductAdapter;
import com.example.tocotoco.home.orderdetail.network.ApiService;
import com.example.tocotoco.model.OrderDetail;
import com.example.tocotoco.model.OrderItemDetail;
import com.example.tocotoco.model.OrderItemDetailResult;
import com.example.tocotoco.util.Utils;

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

public class ActivityOrderDetail extends AppCompatActivity {
    private ActivityOrderDetailBinding binding;
    SharedPreferences sharedPref;
    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    Locale locale = new Locale("vi", "VN");
    NumberFormat currencyFormat = NumberFormat.getNumberInstance(locale);
    private List<OrderItemDetailResult> list = new ArrayList<>();
    private OrderItemProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.backActivity.setOnClickListener(view -> onBackPressed());
        getDataOrderDetail();
        getDataOrderItemDetail();
        Log.e("ID_Order", "" + getOrderID());
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
                Date date = new Date(orderDetail.getResult().getCreateat());
                String ngay = format.format(date);
                binding.tvTime.setText(ngay);
                binding.tvAddress.setText(orderDetail.getResult().getAddress());
                binding.btnOrderStatus.setText(orderDetail.getResult().getStatus());
                binding.tvQuantity.setText("Tạm tính (" + orderDetail.getResult().getTotalProduct() + "món)");
                binding.tvTotal.setText(currencyFormat.format(Integer.parseInt(orderDetail.getResult().getTotal())) + "đ");
                binding.tvPayment.setText(currencyFormat.format(Integer.parseInt(orderDetail.getResult().getTotal())) + "đ" + "(" + orderDetail.getResult().getProvider() + ")");
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