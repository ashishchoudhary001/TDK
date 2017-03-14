package com.thekadesikhaana;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import model.MenuItems;
import model.OrderModel;

/**
 * Created by ParmeshMahore on 2/13/17.
 */

public class PaymentFragment extends Fragment {

    private String totalAmount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(com.thekadesikhaana.R.layout.payment_layout, container, false);
        TextView tvTotalAmount = (TextView) view.findViewById(com.thekadesikhaana.R.id.et_total_amount);
        tvTotalAmount.setText(getTotalAmount());
        return view;
    }

    public String getTotalAmount() {
        int fullAmount = 0;
        for (MenuItems menuItems : OrderModel.getInstance().getMenuItems()) {
            fullAmount += Integer.parseInt(menuItems.getPrice());
        }
        return String.valueOf(fullAmount);
    }
}
