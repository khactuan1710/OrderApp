package com.example.tocotoco.feature.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.gemvietnam.base.ContainerActivity;

public abstract class TCCBaseActivity extends ContainerActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showAlertDialog(String message) {
    }

    public void showProgress() {
    }


    public void hideProgress() {
    }

    public void onRequestError(String errorCode, String errorMessage) {
    }

    @Override
    public void showErrorAlert(Context context, String string) {
    }

    @Override
    public void showNetworkErrorDialog(Activity activity) {
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
