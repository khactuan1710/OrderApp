package com.example.tocotoco.feature.product_detail;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
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
import com.example.tocotoco.feature.cart.CartActivity;
import com.example.tocotoco.feature.login.LoginActivity;
import com.example.tocotoco.feature.login.LoginContract;
import com.example.tocotoco.feature.login.LoginFragment;
import com.example.tocotoco.feature.order.OrderActivity;
import com.example.tocotoco.home.activityhome.HomeActivity;
import com.example.tocotoco.model.LoginResult;
import com.example.tocotoco.model.PhuongResult;
import com.example.tocotoco.model.ProductResult;
import com.example.tocotoco.model.ProductSessionModel;
import com.example.tocotoco.model.ProductsSessionResult;
import com.example.tocotoco.model.QuanResult;
import com.example.tocotoco.model.SessionIdResult;
import com.example.tocotoco.network.AddressPublicAPI;
import com.example.tocotoco.room.TokenDevice;
import com.example.tocotoco.room.TokenDeviceDatabase;
import com.gemvietnam.base.viper.ViewFragment;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
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
    @BindView(R.id.img_cart)
    ImageView img_cart;
    @BindView(R.id.tv_quantity_cart)
    TextView tv_quantity_cart;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_show_price)
    TextView tv_show_price;
    @BindView(R.id.tv_price_old)
    TextView tv_price_old;
    @BindView(R.id.tv_show_price_old)
    TextView tv_show_price_old;
    int totalPrice = 0;
    int totalPriceOld = 0;
    int totalPrice2 = 0;
    int totalPrice2Old = 0;
    private boolean isFav = false;
    private boolean isDetele = false;
    private Intent intent;
    int idProduct;
    int idProductFromLogin = 0;
    int idProductToQuantity = 0;
    String token = "";
    SharedPreferences sharedPref;
    private int quantity = 0;
    int idSession = -1;
    Response<ProductResult> productResult;
    private boolean isSelectQuantity = false;
    private int quantityCart = 0;
    SessionIdResult.SessionId idSessionJs;
    int itemId = 0;
    DecimalFormat formatter = new DecimalFormat("#,###,###");
    private int price = 0;
    private int priceOld = 0;
    private int quantityOld = 0;
    private int priceOneItem = 0;
    private int priceOneItemOld = 0;
    List<TokenDevice> list;
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
        tv_price_old.setPaintFlags(tv_price_old.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        intent = getActivity().getIntent();
        sharedPref = getViewContext().getSharedPreferences(requireContext().getString(R.string.preference_file_key), MODE_PRIVATE);
        token = sharedPref.getString(requireContext().getString(R.string.preference_key_token), "");
        idProduct = intent.getIntExtra("idProduct", 0);
        idProductFromLogin = intent.getIntExtra("goToFavoriteDetail", 0);
        tv_quantity.setText(String.valueOf(quantity));
        mPresenter.getUserShoppingSession(token);
        if(idProductFromLogin != 0) {
            mPresenter.getProductDetail(idProductFromLogin);
            idProductToQuantity = idProductFromLogin;
        }else {
            mPresenter.getProductDetail(idProduct);
            idProductToQuantity = idProduct;
        }
        list = TokenDeviceDatabase.getInstance(getViewContext()).tokenDeviceDAO().getListToken();
    }

    @Override
    public void onResume() {
        super.onResume();
//        mPresenter.getUserShoppingSession(token);
//        initData();
    }

    private void setListener() {
        tv_reduce_quantity.setOnClickListener(this);
        tv_raise_quantity.setOnClickListener(this);
        btn_add_product.setOnClickListener(this);
        img_back.setOnClickListener(this);
        img_fav.setOnClickListener(this);
        img_cart.setOnClickListener(this);
        size_nho.setOnClickListener(this);
        size_vua.setOnClickListener(this);
        size_lon.setOnClickListener(this);
        radioGroup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                if(idProductFromLogin != 0) {
                    Intent i = new Intent(getViewContext(), HomeActivity.class);
                    startActivity(i);
                }else {
                    mPresenter.back();
                }
                break;
            case R.id.img_fav:
                isFav = !isFav;
                isDetele = false;
                if(token.equals("")) {
                    Intent i = new Intent(getViewContext(), LoginActivity.class);
                    i.putExtra("isFavProduct", true);
                    i.putExtra("idProductFromDetail", idProduct);
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
            case R.id.tv_reduce_quantity:
                isSelectQuantity = false;
                if(token.equals("")) {
                    Intent i = new Intent(getViewContext(), LoginActivity.class);
                    i.putExtra("isFavProduct", true);
                    i.putExtra("idProductFromDetail", idProduct);
                    startActivity(i);
                }else {
                    if(quantity > 1) {
                        isDetele = false;
                        mPresenter.addItemToShoppingSession(token, idSession, productResult.body().getResults().getId(), quantity - 1, "M");
//                    quantity --;
//                    tv_quantity.setText(String.valueOf(quantity));
                    }else if (quantity == 1) {
                        isDetele = true;
                        tv_quantity_cart.setText(String.valueOf(quantityCart - 1));
                        mPresenter.deleteItemInShoppingSession(token, itemId, idSession);
                    }
                }
                break;
            case R.id.tv_raise_quantity:
                isDetele = false;
                isSelectQuantity = true;
                if(token.equals("")) {
                    Intent i = new Intent(getViewContext(), LoginActivity.class);
                    i.putExtra("isFavProduct", true);
                    i.putExtra("idProductFromDetail", idProduct);
                    startActivity(i);
                }else {
                    mPresenter.addItemToShoppingSession(token, idSession, productResult.body().getResults().getId(), quantity + 1, "M");
//                    quantity ++;
//                    tv_quantity.setText(String.valueOf(quantity));
                }
                break;
            case R.id.img_cart:
                Intent i = new Intent(getViewContext(), CartActivity.class);
                i.putExtra("tokenToCart", token);
                startActivity(i);
                getViewContext().finish();
                break;
            case R.id.btn_add_product:
                Intent i2;
                if(token.equals("")) {
                    i2 = new Intent(getViewContext(), LoginActivity.class);
                    i2.putExtra("isFavProduct", true);
                    i2.putExtra("idProductFromDetail", idProduct);
                }else {
                    i2 = new Intent(getViewContext(), OrderActivity.class);
                    i2.putExtra("tokenToOrder", token);
                }
                startActivity(i2);
                getViewContext().finish();
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
            productResult = data;
//            tv_price.setText(formatter.format(Integer.parseInt(data.body().getResults().getPrice())) + "đ");
            tv_show_price_old.setText(formatter.format(Integer.parseInt(data.body().getResults().getPrice())) + "đ");
            tv_show_price_old.setPaintFlags(tv_price_old.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tv_show_price.setText(formatter.format(Integer.parseInt(data.body().getResults().getPriceAfterDiscount())) + "đ");
            priceOneItem = Integer.parseInt(data.body().getResults().getPriceAfterDiscount());
            priceOneItemOld = Integer.parseInt(data.body().getResults().getPrice());
            mo_ta_sp.setText(data.body().getResults().getProductDescription());
            tv_name_product.setText(data.body().getResults().getProductName());
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round);



            Glide.with(this).load(data.body().getResults().getDisplayimage()).apply(options).into(img_product);
        }
    }

    @Override
    public void receiveSession(SessionIdResult.SessionId _idSession) {
        idSession = _idSession.getId();
        idSessionJs = _idSession;
    }

    @Override
    public void addItemToCartSuccess(boolean isSuccess) {
        if (isSuccess) {
            if(isSelectQuantity) {
                quantity ++;
                tv_quantity.setText(String.valueOf(quantity));
            }else {
                quantity --;
                tv_quantity.setText(String.valueOf(quantity));
            }
//            if(quantityOld != 0) {
//                quantity = quantityOld;
//            }
            int i = quantity - quantityOld;
            price = priceOneItem * i;
            priceOld = priceOneItemOld * i;
            totalPrice2 = totalPrice + price;
            totalPrice2Old = totalPriceOld + priceOld;
            if(totalPrice2 == 0) {
                tv_price_old.setVisibility(View.GONE);
                tv_price.setVisibility(View.GONE);
            }else {
                tv_price.setVisibility(View.VISIBLE);
                tv_price.setText(formatter.format(totalPrice2) + "đ");
                tv_price_old.setText(formatter.format(totalPrice2Old) + "đ");
            }
            if(!isDetele) {
                if(itemId != 0) {
                    tv_quantity_cart.setText(String.valueOf(quantityCart));
                }else {
                    tv_quantity_cart.setText(String.valueOf(quantityCart + 1));
                    quantityCart = quantityCart + 1;
                }
            }
        }
    }

    @Override
    public void updateQuantityCart(Response<ProductsSessionResult> response, boolean isUpdate) {
        if(isUpdate) {
            for (int i = 0; i < quantityCart; i ++) {
                ProductSessionModel item = response.body().getResults().get(i);
                if(idProductToQuantity == item.getProductId()) {
                    itemId = item.getId();
                }
            }
        }else {
            tv_quantity_cart.setText(String.valueOf(response.body().getResults().size()));
            quantityCart = response.body().getResults().size();
            for (int i = 0; i < quantityCart; i ++) {
                ProductSessionModel item = response.body().getResults().get(i);
//                if(item.getQuantity() != 1) {
//                    totalPrice += Integer.parseInt(item.getPriceAfterDiscount() == null? item.getPrice(): item.getPriceAfterDiscount()) * item.getQuantity();
//                }else {
                totalPrice += Integer.parseInt(item.getPriceAfterDiscount() == null? item.getPrice(): item.getPriceAfterDiscount());
//                }

                totalPriceOld += Integer.parseInt(item.getPrice()) * item.getQuantity();
                if(idProductToQuantity == item.getProductId()) {
                    tv_quantity.setText(String.valueOf(item.getQuantity()));
                    quantityOld = item.getQuantity();
                    quantity = item.getQuantity();
                    itemId = item.getId();
                }
            }
            if(totalPrice != 0) {
                tv_price.setVisibility(View.VISIBLE);
                tv_price.setText(formatter.format(totalPrice) + "đ");
                tv_price_old.setVisibility(View.VISIBLE);
                tv_price_old.setText(formatter.format(totalPriceOld) + "đ");
            }
        }

    }
}