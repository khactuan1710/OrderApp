package com.example.tocotoco.feature.product_detail;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tocotoco.R;
import com.example.tocotoco.feature.login.LoginActivity;
import com.example.tocotoco.feature.login.LoginContract;
import com.example.tocotoco.feature.login.LoginFragment;
import com.example.tocotoco.home.activityhome.HomeActivity;
import com.example.tocotoco.model.LoginResult;
import com.example.tocotoco.model.ProductResult;
import com.gemvietnam.base.viper.ViewFragment;

import butterknife.BindView;
import retrofit2.Response;

public class ProductDetailFragment extends ViewFragment<ProductDetailContract.Presenter> implements ProductDetailContract.View, View.OnClickListener{

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.size_lon)
    RadioButton size_lon;
    @BindView(R.id.size_vua)
    RadioButton size_vua;
    @BindView(R.id.size_nho)
    RadioButton size_nho;
    @BindView(R.id.mo_ta_sp)
    TextView mo_ta_sp;
    @BindView(R.id.tv_name_product)
    TextView tv_name_product;
    @BindView(R.id.img_back)
    ImageView img_back;
    @BindView(R.id.img_fav)
    ImageView img_fav;
    @BindView(R.id.img_product)
    ImageView img_product;
    @BindView(R.id.btn_add_product)
    Button btn_add_product;
    @BindView(R.id.tv_quantity)
    TextView tv_quantity;
    @BindView(R.id.tv_raise_quantity)
    TextView tv_raise_quantity;
    @BindView(R.id.tv_reduce_quantity)
    TextView tv_reduce_quantity;
    private boolean isFav = false;
    private Intent intent;
    int idProduct;
    String token = "";
    SharedPreferences sharedPref;

    public static ProductDetailFragment getInstance() {
        return new ProductDetailFragment();
    }
    @Override
    public void initLayout() {
        super.initLayout();
        setListener();
        initData();
    }

    private void initData() {
        intent = getActivity().getIntent();
        idProduct = intent.getIntExtra("idProduct", 0);
        mPresenter.getProductDetail(idProduct);

        sharedPref = getViewContext().getSharedPreferences(requireContext().getString(R.string.preference_file_key), MODE_PRIVATE);
        token = sharedPref.getString(requireContext().getString(R.string.preference_key_token), "");

    }

    private void setListener() {
        tv_reduce_quantity.setOnClickListener(this);
        tv_raise_quantity.setOnClickListener(this);
        btn_add_product.setOnClickListener(this);
        img_back.setOnClickListener(this);
        img_fav.setOnClickListener(this);
        size_nho.setOnClickListener(this);
        size_vua.setOnClickListener(this);
        size_lon.setOnClickListener(this);
        radioGroup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                mPresenter.back();
                break;
            case R.id.img_fav:
                isFav = !isFav;
                if(!token.equals("")) {
                    Intent i = new Intent(getViewContext(), LoginActivity.class);
                    startActivity(i);
                }else {
                    if(isFav) {
                        mPresenter.addFavItem(token, idProduct);
                        img_fav.setImageResource(R.drawable.fav_icon);
                    }else {
                        mPresenter.deleteFavItem(token, idProduct);
                        img_fav.setImageResource(R.drawable.fav_icon_disable);
                    }
                }
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product_detail;
    }

    @Override
    public void initViewDetail(Response<ProductResult> data) {
        if(data != null) {
            mo_ta_sp.setText(data.body().getResults().getDescription());
            tv_name_product.setText(data.body().getResults().getName());

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round);



            Glide.with(this).load(data.body().getResults().getDisplayimage()).apply(options).into(img_product);
        }
    }
}
