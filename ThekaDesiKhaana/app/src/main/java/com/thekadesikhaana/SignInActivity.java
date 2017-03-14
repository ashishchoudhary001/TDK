package com.thekadesikhaana;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.thekadesikhaana.db.UserDb;

import api.APIClient;
import api.APIInterface;
import model.UserProfileModel;
import model.UserRequestModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Activity to demonstrate basic retrieval of the Google user's ID, email address, and basic
 * profile.
 */
public class SignInActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener, DatePickerFragment.UpdateDateInterface {

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    private GoogleSignInAccount mLoggedInUser;
    private EditText mDateOfBirthEditText;
    private UserRequestModel mNewUser;
    private EditText mPhoneNumber;

    private UserDb mUserDb;
    private RelativeLayout mLoginFormLayout;
    private RelativeLayout mLoginButtonLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.thekadesikhaana.R.layout.signin_activity);

        mUserDb = new UserDb(this);

        mLoginFormLayout = (RelativeLayout) findViewById(com.thekadesikhaana.R.id.login_form_layout);
        mLoginFormLayout.setVisibility(View.INVISIBLE);

        mLoginButtonLayout = (RelativeLayout) findViewById(com.thekadesikhaana.R.id.google_login_button_layout);

        mNewUser = new UserRequestModel();


        // Views
        //mStatusTextView = (TextView) findViewById(R.id.status);

        // Button listeners
        //findViewById(R.id.sign_in_button).setOnClickListener(this);
        //findViewById(R.id.sign_out_button).setOnClickListener(this);
        //findViewById(R.id.disconnect_button).setOnClickListener(this);

        // [START configure_signin]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // [END configure_signin]

        // [START build_client]
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        // [END build_client]

        // [START customize_button]
        // Set the dimensions of the sign-in button.
        RelativeLayout signInButton = (RelativeLayout) findViewById(com.thekadesikhaana.R.id.google_login_button_layout);
        signInButton.setOnClickListener(this);
        //signInButton.setSize(SignInButton.SIZE_WIDE);
        // [END customize_button]
    }

    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    // [START onActivityResult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    // [END onActivityResult]

    // [START handleSignInResult]
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            mLoggedInUser = result.getSignInAccount();

            //mStatusTextView.setText(getString(R.string.signed_in_fmt, detail));
            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }
    // [END handleSignInResult]

    // [START signIn]
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END signIn]

    // [START signOut]
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END signOut]

    // [START revokeAccess]
    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END revokeAccess]

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(com.thekadesikhaana.R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    private void updateUI(boolean signedIn) {
        if (signedIn) {
            mLoginFormLayout.setVisibility(View.VISIBLE);
            mLoginButtonLayout.setVisibility(View.INVISIBLE);

            UserProfileModel userProfileModel = mUserDb.getUserFromDB();
            if(null != userProfileModel) {
               startConfirmActivity(userProfileModel);
            } else {
                findViewById(com.thekadesikhaana.R.id.google_login_button_layout).setVisibility(View.GONE);
                ((AutoCompleteTextView) findViewById(com.thekadesikhaana.R.id.logged_in_user_name)).setText(mLoggedInUser.getDisplayName());
                ((AutoCompleteTextView) findViewById(com.thekadesikhaana.R.id.logged_in_user_email)).setText(mLoggedInUser.getEmail());
                Log.d(TAG, "USER URL: " + mLoggedInUser.getPhotoUrl());
                mDateOfBirthEditText = (EditText) findViewById(com.thekadesikhaana.R.id.edit_text_dob);
                mPhoneNumber = (EditText) findViewById(com.thekadesikhaana.R.id.login_ph_number);

                mNewUser.setName(mLoggedInUser.getDisplayName());
                mNewUser.setEmail(mLoggedInUser.getEmail());

                findViewById(com.thekadesikhaana.R.id.btn_dob).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogFragment newFragment = new DatePickerFragment();
                        newFragment.show(getSupportFragmentManager(), "datePicker");
                    }
                });

                findViewById(com.thekadesikhaana.R.id.btn_create_user).setOnClickListener(this);
            }


        } else {
            //mStatusTextView.setText(R.string.signed_out);

            findViewById(com.thekadesikhaana.R.id.google_login_button_layout).setVisibility(View.VISIBLE);
            //findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        }
    }

    private void startConfirmActivity(UserProfileModel userProfileModel) {
        Intent intent = new Intent(this, ConfirmOrderActivity.class);
        intent.putExtra(Constant.KEY_USER, userProfileModel);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case com.thekadesikhaana.R.id.google_login_button_layout:
                signIn();
                break;
            case com.thekadesikhaana.R.id.btn_create_user:
                //signOut();
                mNewUser.setPhone(mPhoneNumber.getText().toString());
                mNewUser.setGender("m");
                createUser(mNewUser);
                break;
            //case R.id.disconnect_button:
                //revokeAccess();
                //break;
        }
    }

    private void createUser(UserRequestModel newUser) {
        APIInterface apiService =
                APIClient.getClient().create(APIInterface.class);

        Call<UserProfileModel> call = apiService.createUser(newUser);
        call.enqueue(new Callback<UserProfileModel>() {
            @Override
            public void onResponse(Call<UserProfileModel> call, Response<UserProfileModel> response) {
                Log.d(TAG, "user create: " + response.body());
                saveUserInformation(response.body());
            }

            @Override
            public void onFailure(Call<UserProfileModel> call, Throwable t) {
                Toast.makeText(SignInActivity.this, "Network Error, Please Try Again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveUserInformation(UserProfileModel userProfileModel) {
        if(userProfileModel != null) {
            mUserDb.saveUserInDB(userProfileModel);
            startConfirmActivity(userProfileModel);
        } else {
            Log.d(TAG, "USER MODEL IS NULL");
        }
    }

    @Override
    public void onDateSelected(String str) {
        findViewById(com.thekadesikhaana.R.id.btn_dob).setVisibility(View.INVISIBLE);
        mDateOfBirthEditText.setVisibility(View.VISIBLE);
        mDateOfBirthEditText.setText(str);
        mNewUser.setDob(str);
    }
}