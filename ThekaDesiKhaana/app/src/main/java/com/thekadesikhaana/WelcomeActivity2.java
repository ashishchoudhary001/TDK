package com.thekadesikhaana;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import api.APIClient;
import api.APIInterface;
import model.FoodTypeResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ParmeshMahore on 2/12/17.
 */
public class WelcomeActivity2 extends Activity {

    private static final String TAG = WelcomeActivity2.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }


}
