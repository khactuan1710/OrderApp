package com.example.tocotoco.util;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tocotoco.R;
import com.example.tocotoco.feature.changinfo.AddressAdapter;
import com.example.tocotoco.feature.changinfo.ChooseAddressListener;
import com.example.tocotoco.model.CityResult;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class BottomSheetUtil extends BottomSheetDialogFragment {

    private List<CityResult.CityResult2.CityResult3> list;
    private ChooseAddressListener chooseAddressListener;
    private TextView titleDialog;
    private String address = "";
    public BottomSheetUtil(List<CityResult.CityResult2.CityResult3> list, ChooseAddressListener chooseAddressListener, String title) {
        this.list = list;
        this.chooseAddressListener = chooseAddressListener;
        this.address = title;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        View view  = LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_sheet, null);
        bottomSheetDialog.setContentView(view);

        titleDialog = view.findViewById(R.id.tv_title_address);

        titleDialog.setText(address);
        RecyclerView recyclerView = view.findViewById(R.id.rev_address);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        AddressAdapter addressAdapter = new AddressAdapter(list, new ChooseAddressListener() {
            @Override
            public void clickItem(CityResult.CityResult2.CityResult3 c) {
                chooseAddressListener.clickItem(c);
                bottomSheetDialog.dismiss();
            }
        });
        recyclerView.setAdapter(addressAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        return bottomSheetDialog;
    }
}
