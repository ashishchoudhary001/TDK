package com.thekadesikhaana;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Cuisine;
import model.MenuItems;
import model.OrderModel;
import model.UserProfileModel;

/**
 * Created by ParmeshMahore on 2/23/17.
 */

public class ConfirmOrderActivity extends AppCompatActivity {

    private static final String TAG = ConfirmOrderActivity.class.getSimpleName();

    private LinearLayout mBengaliVegLayout;
    private LinearLayout mBengaliNonVegLayout;
    private LinearLayout mNorthVegLayout;
    private LinearLayout mNorthNonVegLayout;
    private LinearLayout mOdiaVegLayout;
    private LinearLayout mOdiaNonVegLayout;
    private LinearLayout mPunjabiVegLayout;
    private LinearLayout mPunjabiNonVegLayout;

    private EditText mBengaliVegItemEditText;
    private EditText mBengaliNonVegItemEditText;
    private EditText mNorthVegItemEditText;
    private EditText mNorthNonVegItemEditText;
    private EditText mOdiaVegItemEditText;
    private EditText mOdiaNonVegItemEditText;
    private EditText mPunjabiVegItemEditText;
    private EditText mPunjabiNonVegItemEditText;

    private UserProfileModel mUserProfile;
    private HashMap<String, List<MenuItems>> mGroupedMap;
    private Spinner mAddressSpinner;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.confirm_order_layout);

        mUserProfile = getIntent().getParcelableExtra(Constant.KEY_USER);
        initViews();


    }

    private void initViews() {
        EditText editText = (EditText) findViewById(R.id.confirm_phone);
        editText.setText(mUserProfile.getPhone());

        mAddressSpinner = (Spinner) findViewById(R.id.address_spinner);

        mBengaliVegLayout = (LinearLayout) findViewById(R.id.bengali_veg_order_layout);
        mBengaliNonVegLayout = (LinearLayout) findViewById(R.id.bengali_nonveg_order_layout);
        mNorthVegLayout = (LinearLayout) findViewById(R.id.north_indian_veg_order_layout);
        mNorthNonVegLayout = (LinearLayout) findViewById(R.id.north_indian_nonveg_order_layout);
        mOdiaVegLayout = (LinearLayout) findViewById(R.id.odia_veg_order_layout);
        mOdiaNonVegLayout = (LinearLayout) findViewById(R.id.odia_nonveg_order_layout);
        mPunjabiVegLayout = (LinearLayout) findViewById(R.id.punjabi_veg_order_layout);
        mPunjabiNonVegLayout = (LinearLayout) findViewById(R.id.punjabi_nonveg_order_layout);

        mBengaliVegItemEditText = (EditText) findViewById(R.id.bengali_veg_item_count_et);
        mBengaliNonVegItemEditText = (EditText) findViewById(R.id.bengali_nonveg_item_count_et);
        mNorthVegItemEditText = (EditText) findViewById(R.id.north_veg_item_count_et);
        mNorthNonVegItemEditText = (EditText) findViewById(R.id.north_nonveg_item_count_et);
        mOdiaVegItemEditText = (EditText) findViewById(R.id.odia_veg_item_count_et);
        mOdiaNonVegItemEditText = (EditText) findViewById(R.id.odia_nonveg_item_count_et);
        mPunjabiVegItemEditText = (EditText) findViewById(R.id.punjabi_veg_item_count_et);
        mPunjabiNonVegItemEditText = (EditText) findViewById(R.id.punjabi_nonveg_item_count_et);

        updateViews();

    }

    private void updateViews() {
        List<MenuItems> menuItemsList = OrderModel.getInstance().getMenuItems();
        printMenuItems(menuItemsList);
        mGroupedMap = OrderModel.getInstance().getGroupedList(menuItemsList);

        if (mGroupedMap.containsKey(Constant.KEY_BENGALI_VEG_LIST)) {

            MenuItems item = mGroupedMap.get(Constant.KEY_BENGALI_VEG_LIST).get(0);
            if (item.getCount() > 0) {
                mBengaliVegLayout.setVisibility(View.VISIBLE);
                mBengaliVegItemEditText.setText(String.valueOf(item.getCount()));
            }
        }

        if (mGroupedMap.containsKey(Constant.KEY_BENGALI_NONVEG_LIST)) {

            MenuItems item = mGroupedMap.get(Constant.KEY_BENGALI_NONVEG_LIST).get(0);
            if (item.getCount() > 0) {
                mBengaliNonVegLayout.setVisibility(View.VISIBLE);
                mBengaliNonVegItemEditText.setText(String.valueOf(item.getCount()));
            }
        }

        if (mGroupedMap.containsKey(Constant.KEY_NORTH_VEG_INDIAN_LIST)) {

            MenuItems item = mGroupedMap.get(Constant.KEY_NORTH_VEG_INDIAN_LIST).get(0);
            if (item.getCount() > 0) {
                mNorthVegLayout.setVisibility(View.VISIBLE);
                mNorthVegItemEditText.setText(String.valueOf(item.getCount()));
            }
        }

        if (mGroupedMap.containsKey(Constant.KEY_NORTH_NONVEG_INDIAN_LIST)) {

            MenuItems item = mGroupedMap.get(Constant.KEY_NORTH_NONVEG_INDIAN_LIST).get(0);
            if (item.getCount() > 0) {
                mNorthNonVegLayout.setVisibility(View.VISIBLE);
                mNorthNonVegItemEditText.setText(String.valueOf(item.getCount()));
            }
        }

        if (mGroupedMap.containsKey(Constant.KEY_ODIA_VEG_LIST)) {

            MenuItems item = mGroupedMap.get(Constant.KEY_ODIA_VEG_LIST).get(0);
            if (item.getCount() > 0) {
                mOdiaVegLayout.setVisibility(View.VISIBLE);
                mOdiaVegItemEditText.setText(String.valueOf(item.getCount()));
            }
        }

        if (mGroupedMap.containsKey(Constant.KEY_ODIA_NONVEG_LIST)) {

            MenuItems item = mGroupedMap.get(Constant.KEY_ODIA_NONVEG_LIST).get(0);
            if (item.getCount() > 0) {
                mOdiaNonVegLayout.setVisibility(View.VISIBLE);
                mOdiaNonVegItemEditText.setText(String.valueOf(item.getCount()));
            }
        }

        if (mGroupedMap.containsKey(Constant.KEY_PUNJABI_VEG_LIST)) {

            MenuItems item = mGroupedMap.get(Constant.KEY_PUNJABI_VEG_LIST).get(0);
            if (item.getCount() > 0) {
                mPunjabiVegLayout.setVisibility(View.VISIBLE);
                mPunjabiVegItemEditText.setText(String.valueOf(item.getCount()));
            }
        }

        if (mGroupedMap.containsKey(Constant.KEY_PUNJABI_NONVEG_LIST)) {

            MenuItems item = mGroupedMap.get(Constant.KEY_PUNJABI_NONVEG_LIST).get(0);
            if (item.getCount() > 0) {
                mPunjabiNonVegLayout.setVisibility(View.VISIBLE);
                mPunjabiNonVegItemEditText.setText(String.valueOf(item.getCount()));
            }
        }
    }

    private void printMenuItems(List<MenuItems> menuItemsList) {
        for (MenuItems item : menuItemsList) {
            Log.d(TAG, item.toString());
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bengali_veg_add_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_BENGALI_VEG_LIST).get(0);
                increaseItemCount(mBengaliVegItemEditText, item);
                break;
            }
            case R.id.bengali_veg_remove_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_BENGALI_VEG_LIST).get(0);
                decreaseItemCount(mBengaliVegItemEditText, item);
                break;
            }
            case R.id.bengali_nonveg_add_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_BENGALI_NONVEG_LIST).get(0);
                increaseItemCount(mBengaliNonVegItemEditText, item);
                break;
            }
            case R.id.bengali_nonveg_remove_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_BENGALI_NONVEG_LIST).get(0);
                decreaseItemCount(mBengaliNonVegItemEditText, item);
                break;
            } ////start north indian///
            case R.id.north_veg_add_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_NORTH_VEG_INDIAN_LIST).get(0);
                increaseItemCount(mNorthVegItemEditText, item);
                break;
            }
            case R.id.north_veg_remove_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_NORTH_VEG_INDIAN_LIST).get(0);
                decreaseItemCount(mNorthVegItemEditText, item);
                break;
            }
            case R.id.north_nonveg_add_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_NORTH_NONVEG_INDIAN_LIST).get(0);
                increaseItemCount(mNorthNonVegItemEditText, item);
                break;
            }
            case R.id.north_nonveg_remove_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_NORTH_NONVEG_INDIAN_LIST).get(0);
                decreaseItemCount(mNorthNonVegItemEditText, item);
                break;
            }////start odia///
            case R.id.odia_veg_add_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_ODIA_VEG_LIST).get(0);
                increaseItemCount(mOdiaVegItemEditText, item);
                break;
            }
            case R.id.odia_veg_remove_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_ODIA_VEG_LIST).get(0);
                decreaseItemCount(mOdiaVegItemEditText, item);
                break;
            }
            case R.id.odia_nonveg_add_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_ODIA_NONVEG_LIST).get(0);
                increaseItemCount(mOdiaNonVegItemEditText, item);
                break;
            }
            case R.id.odia_nonveg_remove_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_ODIA_NONVEG_LIST).get(0);
                decreaseItemCount(mOdiaNonVegItemEditText, item);
                break;
            }////start punjabi///
            case R.id.punjabi_veg_add_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_PUNJABI_VEG_LIST).get(0);
                increaseItemCount(mPunjabiVegItemEditText, item);
                break;
            }
            case R.id.punjab_veg_remove_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_PUNJABI_VEG_LIST).get(0);
                decreaseItemCount(mPunjabiVegItemEditText, item);
                break;
            }
            case R.id.punjabi_nonveg_add_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_PUNJABI_NONVEG_LIST).get(0);
                increaseItemCount(mPunjabiNonVegItemEditText, item);
                break;
            }
            case R.id.punjabi_nonveg_remove_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_PUNJABI_NONVEG_LIST).get(0);
                decreaseItemCount(mPunjabiNonVegItemEditText, item);
                break;
            }
            default:
                Log.d(TAG, "WRONG CASE");
                break;
        }
    }

    private void increaseItemCount(EditText editText, MenuItems item) {

        if (item.getCount() > 0) {
            item.setCount(item.getCount() + 1);
            OrderModel.getInstance().addOrUpdate(item);
            editText.setText(String.valueOf(item.getCount()));
        }
    }

    private void decreaseItemCount(EditText editText, MenuItems item) {

        if (item.getCount() > 0) {
            item.setCount(item.getCount() - 1);
            OrderModel.getInstance().addOrUpdate(item);
            editText.setText(String.valueOf(item.getCount()));
        }
    }
}
