package com.example.tocotoco.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import butterknife.ButterKnife;

public abstract class BaseViewBox extends LinearLayout {
    private int viewId = -1 ;
    public BaseViewBox(Context context) {
        super(context);
        injectView(context);
    }

    public BaseViewBox(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        injectView(context);
    }

    public BaseViewBox(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        injectView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BaseViewBox(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        injectView(context);
    }

    protected abstract int getLayoutId();

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }

    protected void initUI() {

    }

    private void injectView(Context context) {
        View view = inflateView(context);
        ButterKnife.bind(this, view);
        initUI();
    }


    private View inflateView(Context context) {
        return inflate(context, getLayoutId(), this);
    }
}
