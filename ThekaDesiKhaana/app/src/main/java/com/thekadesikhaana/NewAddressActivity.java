package com.thekadesikhaana;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import api.APIClient;
import api.APIInterface;
import model.AddressData;
import model.AddressResponseModel;
import model.NewAddressRequestModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ParmeshMahore on 3/13/17.
 */

public class NewAddressActivity extends AppCompatActivity {

    private static final String TAG = NewAddressActivity.class.getSimpleName();
    private Spinner mAddressTypeSpinner;
    private EditText mPhoneNumber;
    private EditText mAddressLine1;
    private EditText mAddressLine2;
    private EditText mPinCode;
    private EditText mLandMark;
    private Button mCreateAddressButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_address_activity);

        mPhoneNumber = (EditText) findViewById(R.id.new_address_phone_number);
        mAddressLine1 = (EditText) findViewById(R.id.address_line1_edit_text);
        mAddressLine2 = (EditText) findViewById(R.id.address_line2_edit_text);
        mPinCode = (EditText) findViewById(R.id.address_pincode);
        mLandMark = (EditText) findViewById(R.id.address_landmark);
        mAddressTypeSpinner = (Spinner) findViewById(R.id.address_type_spinner);
        mCreateAddressButton = (Button) findViewById(R.id.create_address_button);

        final List<String> list = new ArrayList<>();
        list.add("Home");
        list.add("Office");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_item_layout, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAddressTypeSpinner.setAdapter(dataAdapter);

        mCreateAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "SPINNER ITEM: "+ list.get(mAddressTypeSpinner.getSelectedItemPosition()));
                NewAddressRequestModel addressData = new NewAddressRequestModel();
                addressData.setUserId(mPhoneNumber.getText().toString());
                addressData.setName(list.get(mAddressTypeSpinner.getSelectedItemPosition()));
                addressData.setAddressLine1(mAddressLine1.getText().toString());
                addressData.setAddressLine2(mAddressLine2.getText().toString());
                addressData.setPinCode(mPinCode.getText().toString());
                addressData.setLandmark(mLandMark.getText().toString());
                addressData.setAddressId("0");

                createAddress(addressData);
            }
        });
    }

    private void createAddress(NewAddressRequestModel addressData) {
        APIInterface apiService =
                APIClient.getClient().create(APIInterface.class);

        Call<NewAddressRequestModel> call = apiService.createAddress(addressData);
        call.enqueue(new Callback<NewAddressRequestModel>() {
            @Override
            public void onResponse(Call<NewAddressRequestModel> call, Response<NewAddressRequestModel> response) {
                finish();
            }

            @Override
            public void onFailure(Call<NewAddressRequestModel> call, Throwable t) {
                Toast.makeText(NewAddressActivity.this, "Network Error, Please Try Again!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "" + t.toString());
            }
        });
    }


}
