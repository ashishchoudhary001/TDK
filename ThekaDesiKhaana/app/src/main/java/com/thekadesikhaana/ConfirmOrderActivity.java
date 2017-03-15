package com.thekadesikhaana;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import api.APIClient;
import api.APIInterface;
import model.AddressResponseModel;
import model.ConfirmOrderResponseModel;
import model.CreateOrderRequestModel;
import model.MenuItems;
import model.OrderMenuItemModel;
import model.OrderModel;
import model.UserProfileModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ParmeshMahore on 2/23/17.
 */

public class ConfirmOrderActivity extends AppCompatActivity implements View.OnClickListener {

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

    private Button mChangeAddress;
    private TextView mSelectedAddress;

    private UserProfileModel mUserProfile;
    private HashMap<String, List<MenuItems>> mGroupedMap;
    private Button mSelectAddressButton;

    private List<OrderMenuItemModel> mOrderList;
    private String mAddressId;
    private EditText mPhoneNumberEditText;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(com.thekadesikhaana.R.layout.confirm_order_layout);

        mUserProfile = getIntent().getParcelableExtra(Constant.KEY_USER);
        mOrderList = new ArrayList<>();
        initViews();
    }

    private void initViews() {
        mPhoneNumberEditText = (EditText) findViewById(com.thekadesikhaana.R.id.confirm_phone);
        mPhoneNumberEditText.setText(mUserProfile.getPhone());

        mSelectAddressButton = (Button) findViewById(com.thekadesikhaana.R.id.select_address_btn);
        mSelectAddressButton.setOnClickListener(this);

        mBengaliVegLayout = (LinearLayout) findViewById(com.thekadesikhaana.R.id.bengali_veg_order_layout);
        mBengaliNonVegLayout = (LinearLayout) findViewById(com.thekadesikhaana.R.id.bengali_nonveg_order_layout);
        mNorthVegLayout = (LinearLayout) findViewById(com.thekadesikhaana.R.id.north_indian_veg_order_layout);
        mNorthNonVegLayout = (LinearLayout) findViewById(com.thekadesikhaana.R.id.north_indian_nonveg_order_layout);
        mOdiaVegLayout = (LinearLayout) findViewById(com.thekadesikhaana.R.id.odia_veg_order_layout);
        mOdiaNonVegLayout = (LinearLayout) findViewById(com.thekadesikhaana.R.id.odia_nonveg_order_layout);
        mPunjabiVegLayout = (LinearLayout) findViewById(com.thekadesikhaana.R.id.punjabi_veg_order_layout);
        mPunjabiNonVegLayout = (LinearLayout) findViewById(com.thekadesikhaana.R.id.punjabi_nonveg_order_layout);

        mBengaliVegItemEditText = (EditText) findViewById(com.thekadesikhaana.R.id.bengali_veg_item_count_et);
        mBengaliNonVegItemEditText = (EditText) findViewById(com.thekadesikhaana.R.id.bengali_nonveg_item_count_et);
        mNorthVegItemEditText = (EditText) findViewById(com.thekadesikhaana.R.id.north_veg_item_count_et);
        mNorthNonVegItemEditText = (EditText) findViewById(com.thekadesikhaana.R.id.north_nonveg_item_count_et);
        mOdiaVegItemEditText = (EditText) findViewById(com.thekadesikhaana.R.id.odia_veg_item_count_et);
        mOdiaNonVegItemEditText = (EditText) findViewById(com.thekadesikhaana.R.id.odia_nonveg_item_count_et);
        mPunjabiVegItemEditText = (EditText) findViewById(com.thekadesikhaana.R.id.punjabi_veg_item_count_et);
        mPunjabiNonVegItemEditText = (EditText) findViewById(com.thekadesikhaana.R.id.punjabi_nonveg_item_count_et);

        mChangeAddress = (Button) findViewById(R.id.changeAdd);
        mChangeAddress.setOnClickListener(this);

        mSelectedAddress = (TextView) findViewById(R.id.selectedAddressTV);

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
            case com.thekadesikhaana.R.id.bengali_veg_add_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_BENGALI_VEG_LIST).get(0);
                increaseItemCount(mBengaliVegItemEditText, item);
                break;
            }
            case com.thekadesikhaana.R.id.bengali_veg_remove_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_BENGALI_VEG_LIST).get(0);
                decreaseItemCount(mBengaliVegItemEditText, item);
                break;
            }
            case com.thekadesikhaana.R.id.bengali_nonveg_add_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_BENGALI_NONVEG_LIST).get(0);
                increaseItemCount(mBengaliNonVegItemEditText, item);
                break;
            }
            case com.thekadesikhaana.R.id.bengali_nonveg_remove_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_BENGALI_NONVEG_LIST).get(0);
                decreaseItemCount(mBengaliNonVegItemEditText, item);
                break;
            } ////start north indian///
            case com.thekadesikhaana.R.id.north_veg_add_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_NORTH_VEG_INDIAN_LIST).get(0);
                increaseItemCount(mNorthVegItemEditText, item);
                break;
            }
            case com.thekadesikhaana.R.id.north_veg_remove_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_NORTH_VEG_INDIAN_LIST).get(0);
                decreaseItemCount(mNorthVegItemEditText, item);
                break;
            }
            case com.thekadesikhaana.R.id.north_nonveg_add_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_NORTH_NONVEG_INDIAN_LIST).get(0);
                increaseItemCount(mNorthNonVegItemEditText, item);
                break;
            }
            case com.thekadesikhaana.R.id.north_nonveg_remove_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_NORTH_NONVEG_INDIAN_LIST).get(0);
                decreaseItemCount(mNorthNonVegItemEditText, item);
                break;
            }////start odia///
            case com.thekadesikhaana.R.id.odia_veg_add_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_ODIA_VEG_LIST).get(0);
                increaseItemCount(mOdiaVegItemEditText, item);
                break;
            }
            case com.thekadesikhaana.R.id.odia_veg_remove_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_ODIA_VEG_LIST).get(0);
                decreaseItemCount(mOdiaVegItemEditText, item);
                break;
            }
            case com.thekadesikhaana.R.id.odia_nonveg_add_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_ODIA_NONVEG_LIST).get(0);
                increaseItemCount(mOdiaNonVegItemEditText, item);
                break;
            }
            case com.thekadesikhaana.R.id.odia_nonveg_remove_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_ODIA_NONVEG_LIST).get(0);
                decreaseItemCount(mOdiaNonVegItemEditText, item);
                break;
            }////start punjabi///
            case com.thekadesikhaana.R.id.punjabi_veg_add_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_PUNJABI_VEG_LIST).get(0);
                increaseItemCount(mPunjabiVegItemEditText, item);
                break;
            }
            case com.thekadesikhaana.R.id.punjab_veg_remove_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_PUNJABI_VEG_LIST).get(0);
                decreaseItemCount(mPunjabiVegItemEditText, item);
                break;
            }
            case com.thekadesikhaana.R.id.punjabi_nonveg_add_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_PUNJABI_NONVEG_LIST).get(0);
                increaseItemCount(mPunjabiNonVegItemEditText, item);
                break;
            }
            case com.thekadesikhaana.R.id.punjabi_nonveg_remove_item_iv: {
                MenuItems item = mGroupedMap.get(Constant.KEY_PUNJABI_NONVEG_LIST).get(0);
                decreaseItemCount(mPunjabiNonVegItemEditText, item);
                break;
            }
            case com.thekadesikhaana.R.id.select_address_btn:
            case com.thekadesikhaana.R.id.changeAdd:
                Log.d(TAG, "SELECT ADDRESS BUTTON");
                showAddressListFragment();
                break;
            case com.thekadesikhaana.R.id.button_confirm_order:

                for (List<MenuItems> list : mGroupedMap.values()) {
                    printMenuItems(list);
                    OrderMenuItemModel model = new OrderMenuItemModel();
                    model.setMenuItem(list.get(0).getId());
                    model.setQuantity(String.valueOf(list.get(0).getCount()));
                    mOrderList.add(model);
                }

                CreateOrderRequestModel createOrderRequestModel = new CreateOrderRequestModel();
                createOrderRequestModel.setAddressId(mAddressId);
                createOrderRequestModel.setUserId(mPhoneNumberEditText.getText().toString());
                createOrderRequestModel.setMenuItems(mOrderList);
                createOrderRequestModel.setIsCOD("t");
                createOrderRequestModel.setWalletCashCut("0");
                createOrderRequestModel.setPromotionalWalletCut("0");

                confirmOrder(createOrderRequestModel);

                break;
            default:
                Log.d(TAG, "WRONG CASE");
                break;
        }
    }

    private void confirmOrder(CreateOrderRequestModel createOrderRequestModel) {
        APIInterface apiService =
                APIClient.getClient().create(APIInterface.class);

        showProgressDialog();

        Call<ConfirmOrderResponseModel> call = apiService.createOrder(createOrderRequestModel);
        call.enqueue(new Callback<ConfirmOrderResponseModel>() {
            @Override
            public void onResponse(Call<ConfirmOrderResponseModel> call, Response<ConfirmOrderResponseModel> response) {
                Log.d(TAG, "confirmOrder:" + response.body());
                hideProgessDialog();
                startOrderSummeryActivity(response.body());
            }

            @Override
            public void onFailure(Call<ConfirmOrderResponseModel> call, Throwable t) {
                Toast.makeText(ConfirmOrderActivity.this, "Network Error, Please Try Again!", Toast.LENGTH_SHORT).show();
                hideProgessDialog();
            }
        });
    }

    private void startOrderSummeryActivity(ConfirmOrderResponseModel body) {
        Intent intent = new Intent(ConfirmOrderActivity.this, OrderSummeryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constant.KEY_ORDER_ID, body.getOrderId());
        intent.putExtra(Constant.KEY_ORDER_BUNDLE, bundle);

        startActivity(intent);
    }

    private void showAddressListFragment() {
        Intent intent = new Intent(this, AddressListActivity.class);
        intent.putExtra(Constant.KEY_USER, mUserProfile);
        startActivityForResult(intent, 1022);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == 1022) {
            Bundle bundle = data.getBundleExtra(Constant.KEY_ADDRESS_BUNDLE);
            String address = bundle.getString(Constant.KEY_ADDRESS);
            mAddressId = bundle.getString(Constant.KEY_ADDRESS_ID);
            Log.d(TAG, "ADDRESS: "+address);
            mSelectedAddress.setVisibility(View.VISIBLE);
            mSelectedAddress.setText(address);

            mChangeAddress.setVisibility(View.VISIBLE);
            mSelectAddressButton.setVisibility(View.INVISIBLE);
        } else {
            Log.d(TAG, "ADDRESS not selected");
        }
    }

    private void showProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.show();
    }

    private void hideProgessDialog(){
        if(mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}
