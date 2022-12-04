package com.example.tocotoco.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.tocotoco.R;
import com.gemvietnam.Constants;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

public class EasyDialog extends DialogFragment {
    Activity mActivity;
    EnterListenerBack listener;
    public EasyDialog (Activity activity, EnterListenerBack listener) {
        this.mActivity = activity;
        this.listener = listener;
    }
    @BindView(R.id.tv_complete_status)
    TextView mTvTitle;
    @BindView(R.id.tv_comfirm_submit_voucher_point_money)
    TextView mTVTest;






    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.easy_dialog, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {

        // the content
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        // creating the fullscreen dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.getWindow().setWindowAnimations(R.style.DialogAnimation);
        return dialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        int width = (int) (getContext().getResources().getDisplayMetrics().widthPixels * 0.90);
        getDialog().getWindow().setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @OnClick({R.id.btn_cancel})
    void clickClose(View view){
        switch (view.getId()){
            case R.id.btn_cancel:
                this.dismiss();
                listener.onClose();
                break;
        }

    }

    public interface EnterListenerBack {
        void onClose();
    }

}
