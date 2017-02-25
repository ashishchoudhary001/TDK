package com.thekadesikhaana;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

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

    private ImageButton mBengaliVegRemoveItemButton;
    private ImageButton mBengaliNonVegRemoveItemButton;
    private ImageButton mNorthVegRemoveItemButton;
    private ImageButton mNorthNonVegRemoveItemButton;
    private ImageButton mOdiaVegRemoveItemButton;
    private ImageButton mOdiaNonVegRemoveItemButton;
    private ImageButton mPunjabiVegRemoveItemButton;
    private ImageButton mPunjabiNonVegRemoveItemButton;

    private ImageButton mBengaliVegAddItemButton;
    private ImageButton mBengaliNonVegAddItemButton;
    private ImageButton mNorthVegAddItemButton;
    private ImageButton mNorthNonVegAddItemButton;
    private ImageButton mOdiaVegAddItemButton;
    private ImageButton mOdiaNonVegAddItemButton;
    private ImageButton mPunjabiVegAddItemButton;
    private ImageButton mPunjabiNonVegAddItemButton;

    private EditText mBengaliVegItemEditText;
    private EditText mBengaliNonVegItemEditText;
    private EditText mNorthVegItemEditText;
    private EditText mNorthNonVegItemEditText;
    private EditText mOdiaVegItemEditText;
    private EditText mOdiaNonVegItemEditText;
    private EditText mPunjabiVegItemEditText;
    private EditText mPunjabiNonVegItemEditText;

    private UserProfileModel mUserProfile;

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

        mBengaliVegLayout = (LinearLayout) findViewById(R.id.bengali_veg_order_layout);
        mBengaliNonVegLayout  = (LinearLayout) findViewById(R.id.bengali_nonveg_order_layout);
        mNorthVegLayout  = (LinearLayout) findViewById(R.id.north_indian_veg_order_layout);
        mNorthNonVegLayout  = (LinearLayout) findViewById(R.id.north_indian_nonveg_order_layout);
        mOdiaVegLayout  = (LinearLayout) findViewById(R.id.odia_veg_order_layout);
        mOdiaNonVegLayout  = (LinearLayout) findViewById(R.id.odia_nonveg_order_layout);
        mPunjabiVegLayout  = (LinearLayout) findViewById(R.id.punjabi_veg_order_layout);
        mPunjabiNonVegLayout  = (LinearLayout) findViewById(R.id.punjabi_nonveg_order_layout);

        /*mBengaliVegRemoveItemButton = (ImageButton) mBengaliVegLayout.findViewById(R.id.bengali_veg_remove_item_iv);
        mBengaliNonVegRemoveItemButton = (ImageButton) mBengaliVegLayout.findViewById(R.id.bengali_veg_remove_item_iv);;
        mNorthVegRemoveItemButton = (ImageButton) mBengaliVegLayout.findViewById(R.id.bengali_veg_remove_item_iv);;
        mNorthNonVegRemoveItemButton= (ImageButton) mBengaliVegLayout.findViewById(R.id.bengali_veg_remove_item_iv);;
        mOdiaVegRemoveItemButton= (ImageButton) mBengaliVegLayout.findViewById(R.id.bengali_veg_remove_item_iv);;
        mOdiaNonVegRemoveItemButton= (ImageButton) mBengaliVegLayout.findViewById(R.id.bengali_veg_remove_item_iv);;
        mPunjabiVegRemoveItemButton= (ImageButton) mBengaliVegLayout.findViewById(R.id.bengali_veg_remove_item_iv);;
        mPunjabiNonVegRemoveItemButton= (ImageButton) mBengaliVegLayout.findViewById(R.id.bengali_veg_remove_item_iv);;

        private ImageButton mBengaliVegAddItemButton;
        private ImageButton mBengaliNonVegAddItemButton;
        private ImageButton mNorthVegAddItemButton;
        private ImageButton mNorthNonVegAddItemButton;
        private ImageButton mOdiaVegAddItemButton;
        private ImageButton mOdiaNonVegAddItemButton;
        private ImageButton mPunjabiVegAddItemButton;
        private ImageButton mPunjabiNonVegAddItemButton;*/

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
        HashMap<String, List<MenuItems>> groupedMap = getGroupedList(menuItemsList);

        if(groupedMap.containsKey(Constant.KEY_BENGALI_VEG_LIST)) {
            mBengaliVegLayout.setVisibility(View.VISIBLE);
            mBengaliVegItemEditText.setText(String.valueOf(groupedMap.get(Constant.KEY_BENGALI_VEG_LIST).size()));
        }

        if(groupedMap.containsKey(Constant.KEY_BENGALI_NONVEG_LIST)) {
            mBengaliNonVegLayout.setVisibility(View.VISIBLE);
            mBengaliNonVegItemEditText.setText(String.valueOf(groupedMap.get(Constant.KEY_BENGALI_NONVEG_LIST).size()));
        }

        if(groupedMap.containsKey(Constant.KEY_NORTH_VEG_INDIAN_LIST)) {
            mNorthVegLayout.setVisibility(View.VISIBLE);
            mNorthVegItemEditText.setText(String.valueOf(groupedMap.get(Constant.KEY_NORTH_VEG_INDIAN_LIST).size()));
        }

        if(groupedMap.containsKey(Constant.KEY_NORTH_NONVEG_INDIAN_LIST)) {
            mNorthNonVegLayout.setVisibility(View.VISIBLE);
            mNorthNonVegItemEditText.setText(String.valueOf(groupedMap.get(Constant.KEY_NORTH_NONVEG_INDIAN_LIST).size()));
        }

        if (groupedMap.containsKey(Constant.KEY_ODIA_VEG_LIST)) {
            mOdiaVegLayout.setVisibility(View.VISIBLE);
            mOdiaVegItemEditText.setText(String.valueOf(groupedMap.get(Constant.KEY_ODIA_VEG_LIST).size()));
        }

        if(groupedMap.containsKey(Constant.KEY_ODIA_NONVEG_LIST)) {
            mOdiaVegLayout.setVisibility(View.VISIBLE);
            mOdiaNonVegItemEditText.setText(String.valueOf(groupedMap.get(Constant.KEY_ODIA_NONVEG_LIST).size()));
        }

        if(groupedMap.containsKey(Constant.KEY_PUNJABI_VEG_LIST)) {
            mPunjabiVegLayout.setVisibility(View.VISIBLE);
            mPunjabiVegItemEditText.setText(String.valueOf(groupedMap.get(Constant.KEY_PUNJABI_VEG_LIST).size()));
        }

        if(groupedMap.containsKey(Constant.KEY_PUNJABI_NONVEG_LIST)) {
            mPunjabiVegLayout.setVisibility(View.VISIBLE);
            mPunjabiNonVegItemEditText.setText(String.valueOf(groupedMap.get(Constant.KEY_PUNJABI_NONVEG_LIST).size()));
        }
    }

    private HashMap<String, List<MenuItems>> getGroupedList(List<MenuItems> menuItemsList) {
        List<MenuItems> vegBengali = new ArrayList<>();
        List<MenuItems> vegNorthIndian = new ArrayList<>();
        List<MenuItems> vegOdia = new ArrayList<>();
        List<MenuItems> vegPunjabi = new ArrayList<>();

        List<MenuItems> nonVegBengali = new ArrayList<>();
        List<MenuItems> nonVegNorthIndian = new ArrayList<>();
        List<MenuItems> nonVegOdia = new ArrayList<>();
        List<MenuItems> nonVegPunjabi = new ArrayList<>();

        for(MenuItems item : menuItemsList) {
            Cuisine cuisine = item.getCuisine();

            if(cuisine.getName().equalsIgnoreCase(Constant.FOOD_TYPE_BENGALI)) {
                if(cuisine.getType().equalsIgnoreCase("v")) {
                    vegBengali.add(item);
                } else {
                    nonVegBengali.add(item);
                }
            } else if(cuisine.getName().equalsIgnoreCase(Constant.FOOD_TYPE_NORTH_INDIAN)) {
                if(cuisine.getType().equalsIgnoreCase("v")) {
                    vegNorthIndian.add(item);
                } else {
                    nonVegNorthIndian.add(item);
                }
            } else if(cuisine.getName().equalsIgnoreCase(Constant.FOOD_TYPE_ODIA)) {
                if(cuisine.getType().equalsIgnoreCase("v")) {
                    vegOdia.add(item);
                } else {
                    nonVegOdia.add(item);
                }
            } else if(cuisine.getName().equalsIgnoreCase(Constant.FOOD_TYPE_PUNJABI)) {
                if(cuisine.getType().equalsIgnoreCase("v")) {
                    vegPunjabi.add(item);
                } else {
                    nonVegPunjabi.add(item);
                }
            }
        }

        HashMap<String, List<MenuItems>> itemList = new HashMap<>();
        if(vegBengali.size() > 0)
            itemList.put(Constant.KEY_BENGALI_VEG_LIST, vegBengali);
        if(nonVegBengali.size() > 0)
            itemList.put(Constant.KEY_BENGALI_NONVEG_LIST, nonVegBengali);
        if(vegNorthIndian.size() > 0)
            itemList.put(Constant.KEY_NORTH_VEG_INDIAN_LIST, vegNorthIndian);
        if(nonVegNorthIndian.size() > 0)
            itemList.put(Constant.KEY_NORTH_NONVEG_INDIAN_LIST, nonVegNorthIndian);
        if(vegOdia.size() > 0)
            itemList.put(Constant.KEY_ODIA_VEG_LIST, vegOdia);
        if(nonVegOdia.size() > 0)
            itemList.put(Constant.KEY_ODIA_NONVEG_LIST, nonVegOdia);
        if(vegPunjabi.size() > 0)
            itemList.put(Constant.KEY_PUNJABI_VEG_LIST, vegPunjabi);
        if(nonVegPunjabi.size() > 0)
            itemList.put(Constant.KEY_PUNJABI_NONVEG_LIST, nonVegPunjabi);

        return itemList;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bengali_veg_add_item_iv:
                mBengaliVegItemEditText.getText();
        }
    }
}
