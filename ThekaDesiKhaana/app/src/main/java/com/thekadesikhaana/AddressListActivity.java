package com.thekadesikhaana;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.thekadesikhaana.adapter.AddressListAdapter;

import java.util.List;

import api.APIClient;
import api.APIInterface;
import model.AddressData;
import model.AddressResponseModel;
import model.UserAddressResponseModel;
import model.UserProfileModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ParmeshMahore on 3/4/17.
 */

public class AddressListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = AddressListActivity.class.getSimpleName();
    private AddressListAdapter mAddressListAdapter;
    private List<AddressData> mAddressList;
    private Button mAddAddressButton;
    private UserProfileModel mUserProfile;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.thekadesikhaana.R.layout.fragment_address_list);

        mUserProfile = getIntent().getParcelableExtra(Constant.KEY_USER);
        Log.d(TAG, mUserProfile.toString());

        mAddressListAdapter = new AddressListAdapter(this);
        ListView addressList = (ListView) findViewById(R.id.address_list);

        mAddAddressButton = (Button) findViewById(R.id.add_address_button);

        mAddAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "START NEW ADDRESS FORM");
                Intent intent = new Intent(AddressListActivity.this, NewAddressActivity.class);
                startActivity(intent);
            }
        });

        addressList.setAdapter(mAddressListAdapter);
        addressList.setOnItemClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchAddressList(mUserProfile.getPhone());
    }

    private void fetchAddressList(String phoneNumber) {
        APIInterface apiService =
                APIClient.getClient().create(APIInterface.class);

        Call<AddressResponseModel> call = apiService.getUserAddress(phoneNumber);
        call.enqueue(new Callback<AddressResponseModel>() {
            @Override
            public void onResponse(Call<AddressResponseModel> call, Response<AddressResponseModel> response) {
                Log.d(TAG, "" + response.body());
                mAddressList = response.body().getData();
                if(mAddressList.size() > 0) {
                    mAddressListAdapter.setData(mAddressList);
                    mAddAddressButton.setVisibility(View.INVISIBLE);
                } else {
                    mAddAddressButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<AddressResponseModel> call, Throwable t) {
                Toast.makeText(AddressListActivity.this, "Network Error, Please Try Again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        AddressData data = mAddressList.get(position);

        String address = data.getName() + " " + data.getAddressLine1() +" " +data.getAddressLine2()
                +" " +data.getLandmark() +" " + data.getPinCode();

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.KEY_ADDRESS, address);

        intent.putExtra(Constant.KEY_ADDRESS_BUNDLE, bundle);

        setResult(1022, intent);
        finish();
    }
}
