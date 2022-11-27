package com.example.tocotoco.network;

import android.content.Context;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class TCCCallback<T> implements Callback<T> {
    private Context mContext;
    private boolean isInternalErrorDisplayed = true;

    public abstract void onTCTCSuccess(Call<T> call, Response<T> response);

    public abstract void onTCTCFailure(Call<T> call);
    protected void onTCTCFailure(Call<T> call, Response<T> response) {
        //ham nay duoc su dung trong TH neu can check response.code
    }
    public TCCCallback() {
        mContext = null;
        this.isInternalErrorDisplayed = false;
    }

    public TCCCallback(boolean isInternalErrorDisplayed) {
        this.isInternalErrorDisplayed = isInternalErrorDisplayed;
    }

    public TCCCallback(Context context) {
        mContext = context;
        this.isInternalErrorDisplayed = true;
    }

    public TCCCallback(Context context, boolean isInternalErrorDisplayed) {
        mContext = context;
        this.isInternalErrorDisplayed = isInternalErrorDisplayed;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        if (response != null && response.code() >= 200 && response.code() < 300 && response.body() != null) {
            onTCTCSuccess(call, response);
        } else if (response != null && (response.code() < 200 || response.code() >= 300)) {
            if (mContext != null && isInternalErrorDisplayed) {
//        android.widget.Toast.makeText(mContext, R.string.error_system_upgrading, android.widget.Toast.LENGTH_LONG).show();
            }
            this.onTCTCFailure(call);
            this.onTCTCFailure(call,response);
        } else {
            if (mContext != null && this.isInternalErrorDisplayed) {
//        Toast.showToast(mContext, R.string.error_fail_default);
            }
            this.onTCTCFailure(call);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (mContext != null && this.isInternalErrorDisplayed) {
//      Toast.showToast(mContext, R.string.error_fail_default);
        }
        Log.e("LOG onFailure: ", call.request().url() + " " + t.toString());
        this.onTCTCFailure(call);
    }

    public void setShowErrorMessage(boolean showErrorMessage) {
        isInternalErrorDisplayed = showErrorMessage;
    }
}
