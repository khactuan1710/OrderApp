package com.example.tocotoco.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.example.tocotoco.R;

public class NProgressDialog extends Dialog {
    View progressBar;
    View tvWait;
    boolean check = false;

    public NProgressDialog(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        setContentView(R.layout.dialog_progress);
        progressBar = findViewById(R.id.progress_bar);
        this.setCancelable(false);
    }
    public NProgressDialog(Context context,boolean isShowText) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        setContentView(R.layout.dialog_progress);
        progressBar = findViewById(R.id.progress_bar);
        tvWait = findViewById(R.id.tv_wait);
        if(isShowText){
            tvWait.setVisibility(View.VISIBLE);
        }else {
            tvWait.setVisibility(View.GONE);
        }
        this.setCancelable(false);
    }
    @Override
    public void show() {
        try {
            progressBar.setVisibility(View.VISIBLE);
            super.show();
        } catch (Exception e) {

        }
    }
}
