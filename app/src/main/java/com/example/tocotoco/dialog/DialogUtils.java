package com.example.tocotoco.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class DialogUtils {
    private static volatile NProgressDialog progress;
    private static Activity staticActivity;
    public static void showProgressDialog(final Activity activity) {
        if (activity == null)
            return;

        if (progress != null) {
            try {
                progress.cancel();
                staticActivity = activity;
            } catch (Exception ex) {
//                MyLogger.log(ex);
            }
        }


        hideKeyboard(null, activity);
        progress = new NProgressDialog(activity);
        progress.setTitle("");
        progress.setCancelable(true);
        progress.setOnCancelListener(null);
        progress.setCanceledOnTouchOutside(false);
        progress.show();
    }

    public static void dismissProgressDialog() {
        try {
            if (progress != null)
                progress.dismiss();
        } catch (Exception e) {
//            Log.e(TAG, "dismissProgressDialog Exception", e);
        }
    }


    public static void hideKeyboard(View focusingView, Activity context) {
        try {
            InputMethodManager imm = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            if (focusingView != null) {
                imm.hideSoftInputFromWindow(focusingView.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            } else {
                imm.hideSoftInputFromWindow(context.getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception e) {
        }
    }
}
