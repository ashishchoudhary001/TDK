package com.thekadesikhaana;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import api.APIClient;
import api.APIInterface;
import model.AddressResponseModel;
import model.OrderDetailModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ParmeshMahore on 3/15/17.
 */

public class OrderSummeryActivity extends AppCompatActivity {

    private static final String TAG = OrderSummeryActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_order_summery);

        Bundle bundle = getIntent().getBundleExtra(Constant.KEY_ORDER_BUNDLE);
        String orderId = bundle.getString(Constant.KEY_ORDER_ID);
        fetOrderStatus(orderId);
    }


    private void fetOrderStatus(String orderId) {
        APIInterface apiService =
                APIClient.getClient().create(APIInterface.class);

        Call<OrderDetailModel> call = apiService.getOrderSummery(orderId);
        call.enqueue(new Callback<OrderDetailModel>() {
            @Override
            public void onResponse(Call<OrderDetailModel> call, Response<OrderDetailModel> response) {
                Log.d(TAG, "orderSummery" + response.body());

            }

            @Override
            public void onFailure(Call<OrderDetailModel> call, Throwable t) {

            }
        });
    }
}
