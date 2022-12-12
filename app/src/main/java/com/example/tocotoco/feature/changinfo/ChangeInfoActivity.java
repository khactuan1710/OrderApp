package com.example.tocotoco.feature.changinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tocotoco.R;
import com.example.tocotoco.dialog.DialogUtils;
import com.example.tocotoco.feature.order.OrderActivity;
import com.example.tocotoco.model.CityResult;
import com.example.tocotoco.model.PhuongResult;
import com.example.tocotoco.model.QuanResult;
import com.example.tocotoco.network.AddressPublicAPI;
import com.example.tocotoco.util.BottomSheetUtil;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeInfoActivity extends AppCompatActivity {
    RelativeLayout btn_tinh;
    RelativeLayout btn_quan;
    RelativeLayout btn_thon;
    RelativeLayout btn_mota;
    TextView tv_rq_tinh;
    TextView tv_rq_quan;
    TextView tv_rq_thon;
    TextView tv_tinh;
    TextView tv_quan;
    TextView tv_thon;
    TextView tv_address_new;
    Button btn_save;
    EditText ed_mota;
    TextInputEditText ed_name;
    TextInputEditText ed_phone;
    ImageView img;
    List<CityResult.CityResult2.CityResult3> listTinh;
    List<CityResult.CityResult2.CityResult3> listQuan;
    List<CityResult.CityResult2.CityResult3> listThon;
    String provinceCode= "";
    String q= "";
    String cols= "parent_code";
    private boolean isSave = false;
    private boolean isSelectTinh = false;
    private boolean isSelectQuan = false;
    private boolean isSelectThon = false;
    private Context context;
    private String addressNew = "";

    String name;
    String phone;
    private Intent intent;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
        btn_tinh = findViewById(R.id.btn_tinh);
        btn_quan = findViewById(R.id.btn_quan);
        btn_thon = findViewById(R.id.btn_thon);
        btn_mota = findViewById(R.id.btn_mota);
        tv_rq_tinh = findViewById(R.id.rq_tinh);
        tv_rq_quan = findViewById(R.id.rq_quan);
        tv_rq_thon = findViewById(R.id.rq_thon);
        tv_tinh = findViewById(R.id.tv_tinh);
        tv_quan = findViewById(R.id.tv_quan);
        tv_thon = findViewById(R.id.tv_thon);
        tv_address_new = findViewById(R.id.tv_address_new);
        btn_save = findViewById(R.id.btn_save);
        ed_mota = findViewById(R.id.ed_mota);
        ed_name = findViewById(R.id.ed_name);
        ed_phone = findViewById(R.id.ed_sdt);
        img = findViewById(R.id.ic_back);
        context = this;

        intent = getIntent();
        name = intent.getStringExtra("nameFromOrderFragment");
        phone = intent.getStringExtra("phoneFromOrderFragment");
        ed_name.setText(name);
        ed_phone.setText(phone);
        btn_save.setClickable(false);
        btn_save.setEnabled(false);
        btn_tinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddressPublicAPI.API_SERVICE.getListTinh( "-1").enqueue(new Callback<CityResult>() {
                    @Override
                    public void onResponse(Call<CityResult> call, Response<CityResult> response) {
                        listTinh = response.body().getData().getData();
                        selectTinh();
                    }

                    @Override
                    public void onFailure(Call<CityResult> call, Throwable t) {

                    }
                });
            }
        });


        btn_quan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isSelectTinh) {
                    AddressPublicAPI.API_SERVICE.getListQuan(provinceCode, "-1").enqueue(new Callback<CityResult>() {
                        @Override
                        public void onResponse(Call<CityResult> call, Response<CityResult> response) {
                            listQuan = response.body().getData().getData();
                            selectQuan();
                        }

                        @Override
                        public void onFailure(Call<CityResult> call, Throwable t) {

                        }
                    });
                } else {
                    Toast.makeText(context, "Bạn phải chọn Tỉnh/Thành phố trước", Toast.LENGTH_LONG).show();
                }
            }});


            btn_thon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isSelectQuan) {
                        AddressPublicAPI.API_SERVICE.getListPhuong(q, cols, "-1").enqueue(new Callback<CityResult>() {
                            @Override
                            public void onResponse(Call<CityResult> call, Response<CityResult> response) {
                                listThon = response.body().getData().getData();
                                selectThon();
                            }

                            @Override
                            public void onFailure(Call<CityResult> call, Throwable t) {

                            }
                        });
                    }else {
                        Toast.makeText(context, "Bạn phải chọn Quận/Huyện trước", Toast.LENGTH_LONG).show();

                    }
                }
            });


        ed_mota.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tv_address_new.setText(charSequence + ", " + addressNew);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(isSelectThon && ed_mota.getText().length() != 0) {
                    btn_save.setClickable(true);
                    btn_save.setEnabled(true);
                    btn_save.setBackground(ContextCompat.getDrawable(context, R.drawable.border_item_button_add));
                }else if(ed_mota.getText().length() == 0) {
                    btn_save.setBackground(ContextCompat.getDrawable(context, R.drawable.border_item_button_cancel));
                    btn_save.setClickable(false);
                    btn_save.setEnabled(false);
                }
            }
        });

        ed_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(ed_name.getText().length() == 0) {
                    btn_save.setBackground(ContextCompat.getDrawable(context, R.drawable.border_item_button_cancel));
                    btn_save.setClickable(false);
                    btn_save.setEnabled(false);
                }
            }
        });

        ed_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(ed_phone.getText().length() == 0) {
                    btn_save.setBackground(ContextCompat.getDrawable(context, R.drawable.border_item_button_cancel));
                    btn_save.setClickable(false);
                    btn_save.setEnabled(false);
                }
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showProgressDialog((Activity) context);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent i = new Intent(context, OrderActivity.class);
                        i.putExtra("nameFromOrderChangeInfo", ed_name.getText().toString());
                        i.putExtra("phoneFromOrderChangeInfo", ed_phone.getText().toString());
                        i.putExtra("addressFromOrderChangeInfo", tv_address_new.getText().toString());
                        startActivity(i);
                        finish();
                        DialogUtils.dismissProgressDialog();
                    }
                }, 100);
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, OrderActivity.class);
                startActivity(i);
                finish();

            }
        });

    }

    private void selectTinh() {
        BottomSheetUtil bottomSheetUtil = new BottomSheetUtil(listTinh, new ChooseAddressListener() {
            @Override
            public void clickItem(CityResult.CityResult2.CityResult3 c) {
                provinceCode = c.getCode();
                isSelectTinh = true;
                tv_address_new.setText(c.getName_with_type());
                addressNew = c.getName_with_type();
                tv_tinh.setText(c.getName());


                isSelectQuan = false;
                isSelectThon = false;
                tv_thon.setText("Chọn Phường/Xã*");
                tv_quan.setText("Chọn Huyện/Quận*");
                btn_save.setBackground(ContextCompat.getDrawable(context, R.drawable.border_item_button_cancel));
                btn_save.setClickable(false);
                btn_save.setEnabled(false);
            }
        }, "Chọn Tỉnh/Thành phố");
        bottomSheetUtil.show(getSupportFragmentManager(), bottomSheetUtil.getTag());
    }
    private void selectQuan() {
        BottomSheetUtil bottomSheetUtil = new BottomSheetUtil(listQuan, new ChooseAddressListener() {
            @Override
            public void clickItem(CityResult.CityResult2.CityResult3 c) {
                q = c.getCode();
                isSelectQuan = true;
                tv_address_new.setText(c.getPath_with_type());
                addressNew = c.getPath_with_type();
                tv_quan.setText(c.getName());



                isSelectThon = false;
                tv_thon.setText("Chọn Phường/Xã*");
                btn_save.setBackground(ContextCompat.getDrawable(context, R.drawable.border_item_button_cancel));
                btn_save.setClickable(false);
                btn_save.setEnabled(false);
            }
        }, "Chọn Quận/Huyện");
        bottomSheetUtil.show(getSupportFragmentManager(), bottomSheetUtil.getTag());
    }
    private void selectThon() {
        BottomSheetUtil bottomSheetUtil = new BottomSheetUtil(listThon, new ChooseAddressListener() {
            @Override
            public void clickItem(CityResult.CityResult2.CityResult3 c) {
                isSelectThon = true;
                tv_thon.setText(c.getName());
                tv_address_new.setText(c.getPath_with_type());
                addressNew = c.getPath_with_type();
                if(ed_mota.getText().length() != 0){
                    btn_save.setClickable(true);
                    btn_save.setEnabled(true);
                    btn_save.setBackground(ContextCompat.getDrawable(context, R.drawable.border_item_button_add));
                }
            }
        }, "Chọn Phường/Xã");
        bottomSheetUtil.show(getSupportFragmentManager(), bottomSheetUtil.getTag());
    }
}